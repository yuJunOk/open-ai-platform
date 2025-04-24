1. 初始化ant-design-pro 项目

```
npm i @ant-design/pro-cli -g
pro create 项目名

初始化选择
? 🚀 要全量的还是一个简单的脚手架? simple

加入项目 安装项目自带依赖
npm install
```

2. 项目瘦身
  1. 在package.json中寻找i18n-remove命令，运行，删除多语言环境配置
  2. 删除src.locals文件夹，里面存着多语言的配置数据
  3. 删除.husky文件夹，这是提交代码时的检测配置
  4. 删除test文件夹，这是一些测试程序
3. 可以通过oneapi快速生成前端请求方法和请求数据类型等文件，后可直接调用生成方法请求数据
4. 在typings.d.ts添加全局状态类型
5. 添加echart

```
npm install --save echarts-for-react

npm install --save echarts
```

