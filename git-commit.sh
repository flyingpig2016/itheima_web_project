#!/bin/bash

# 设置颜色
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
NC='\033[0m' # No Color

# 检查是否提供了提交信息
if [ -z "$1" ]; then
    echo -e "${RED}错误: 请提供提交信息${NC}"
    echo -e "使用方法: $0 \"提交信息\""
    echo -e "例如: $0 \"告警问题联调\""
    exit 1
fi

# 保存提交信息
COMMIT_MESSAGE="$1"

echo -e "${YELLOW}开始提交代码...${NC}"
echo -e "${YELLOW}提交信息: ${COMMIT_MESSAGE}${NC}"

# 显示当前修改状态
echo -e "${GREEN}当前文件状态：${NC}"
git status

# 添加所有文件
git add .

# 提交代码
git commit -m "$COMMIT_MESSAGE"

# 推送到远程仓库
echo -e "${YELLOW}正在推送到远程仓库...${NC}"
if git push; then
    echo -e "${GREEN}推送成功！${NC}"
else
    echo -e "${RED}推送失败，请检查网络或远程仓库状态${NC}"
    exit 1
fi

# 显示提交结果
echo -e "${GREEN}提交完成！${NC}"
echo -e "${YELLOW}提交详情：${NC}"
git show --stat HEAD