#!/bin/bash

# 农作物信息管理系统 - API自动化测试脚本

BASE_URL="http://localhost:8080/crop"

echo "=========================================="
echo "农作物信息管理系统 - API自动化测试"
echo "=========================================="
echo ""

# 测试颜色
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# 测试计数器
PASS=0
FAIL=0

# 测试函数
test_api() {
    local name=$1
    local method=$2
    local url=$3
    local data=$4
    local expected=$5

    echo -n "[$name] $method $url ... "

    if [ "$method" = "GET" ]; then
        response=$(curl -s -w "\n%{http_code}" -X GET "$url")
        http_code=$(echo "$response" | tail -1)
        body=$(echo "$response" | head -n -1)
    elif [ "$method" = "POST" ]; then
        response=$(curl -s -w "\n%{http_code}" -X POST "$url" -H "Content-Type: application/json" -d "$data")
        http_code=$(echo "$response" | tail -1)
        body=$(echo "$response" | head -n -1)
    elif [ "$method" = "PUT" ]; then
        response=$(curl -s -w "\n%{http_code}" -X PUT "$url" -H "Content-Type: application/json" -d "$data")
        http_code=$(echo "$response" | tail -1)
        body=$(echo "$response" | head -n -1)
    elif [ "$method" = "DELETE" ]; then
        response=$(curl -s -w "\n%{http_code}" -X DELETE "$url")
        http_code=$(echo "$response" | tail -1)
        body=$(echo "$response" | head -n -1)
    fi

    if [ "$http_code" = "$expected" ]; then
        echo -e "${GREEN}✅ PASS${NC} (HTTP $http_code)"
        ((PASS++))
        return 0
    else
        echo -e "${RED}❌ FAIL${NC} (HTTP $http_code, expected $expected)"
        echo "  Response: $body"
        ((FAIL++))
        return 1
    fi
}

# 检查服务是否运行
echo -e "${YELLOW}[检查服务]${NC}"
health=$(curl -s -o /dev/null -w "%{http_code}" http://localhost:8080/crop/list 2>/dev/null)
if [ "$health" != "200" ] && [ "$health" != "500" ]; then
    echo -e "${RED}❌ 服务未启动或无法访问 http://localhost:8080${NC}"
    echo "请先运行: mvn spring-boot:run"
    exit 1
fi
echo -e "${GREEN}✅ 服务正在运行${NC}"
echo ""

echo "=========================================="
echo "开始测试..."
echo "=========================================="
echo ""

# 1. 测试添加农作物 (POST /crop)
echo -e "${YELLOW}[测试 1/5] 添加农作物${NC}"
CROP_JSON='{"name":"水稻","category":"粮食","growthCycle":120}'
test_api "添加农作物" "POST" "$BASE_URL" "$CROP_JSON" "200"
echo ""

# 2. 测试添加第二个农作物
echo -e "${YELLOW}[测试 2/5] 添加第二个农作物${NC}"
CROP_JSON2='{"name":"小麦","category":"粮食","growthCycle":100}'
test_api "添加第二个农作物" "POST" "$BASE_URL" "$CROP_JSON2" "200"
echo ""

# 3. 测试查询所有农作物 (GET /crop/list)
echo -e "${YELLOW}[测试 3/5] 查询所有农作物${NC}"
test_api "查询所有农作物" "GET" "$BASE_URL/list" "" "200"
echo ""

# 4. 测试根据ID查询 (GET /crop/{id})
echo -e "${YELLOW}[测试 4/5] 根据ID查询农作物${NC}"
test_api "根据ID查询" "GET" "$BASE_URL/1" "" "200"
echo ""

# 5. 测试更新农作物 (PUT /crop/{id})
echo -e "${YELLOW}[测试 5/5] 更新农作物${NC}"
UPDATE_JSON='{"name":"玉米","category":"粮食","growthCycle":90}'
test_api "更新农作物" "PUT" "$BASE_URL/1" "$UPDATE_JSON" "200"
echo ""

# 6. 验证更新结果
echo -e "${YELLOW}[额外测试] 验证更新结果${NC}"
result=$(curl -s -X GET "$BASE_URL/1")
if echo "$result" | grep -q "玉米"; then
    echo -e "${GREEN}✅ 更新验证成功${NC}"
    ((PASS++))
else
    echo -e "${RED}❌ 更新验证失败${NC}"
    echo "  Response: $result"
    ((FAIL++))
fi
echo ""

# 测试删除 (单独测试，可能影响后续测试)
echo -e "${YELLOW}[额外测试] 删除农作物${NC}"
test_api "删除农作物" "DELETE" "$BASE_URL/2" "" "204"
echo ""

echo "=========================================="
echo "测试完成"
echo "=========================================="
echo -e "${GREEN}通过: $PASS${NC}"
echo -e "${RED}失败: $FAIL${NC}"
echo ""

if [ $FAIL -eq 0 ]; then
    echo -e "${GREEN}🎉 所有测试通过！${NC}"
    exit 0
else
    echo -e "${RED}⚠️  有 $FAIL 个测试失败${NC}"
    exit 1
fi
