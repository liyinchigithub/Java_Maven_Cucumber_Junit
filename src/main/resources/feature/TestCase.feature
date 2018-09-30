# language: zh-CN
功能:
  背景：已登录成功
  @Test01

  场景大纲:作为百度的用户,我希望能够在百度首页登录成功,以便于进行相关用户配置
    假如 打开"<URL>"
    当 用户点击首页登录
    并且 弹出登录方式窗口
    当 选择用户名登录方式
    并且 输入账号"<Username>"
    并且 输入密码"<Password>"
    那么 点击登录后，登录成功"<Username>"
    例子:
      |         URL            |     Username    |     Password      |
      | http://wwww.baidu.com  |       李银池     |      88888       |