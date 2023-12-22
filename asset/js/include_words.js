$(function () {
    var hasRustWeb = false;

    $('.article').find('.posted-in').each(function () {
      if ($(this).text().trim() === "Rust web") {
        hasRustWeb = true;
        return false; // 结束循环
      }
    });

    if (hasRustWeb) {
      console.log("Rust web 标签存在");
      // 在这里可以执行你想要的操作
      var rust_web_content = '<p>本系列文章从以下几个方面学习如何使用 Rust 进行 web 开发。</p>\n' +
          '<ol>\n' +
          '<li>web 框架</li>\n' +
          '<li>数据库/orm</li>\n' +
          '<li>config</li>\n' +
          '<li>log</li>\n' +
          '<li>线程池</li>\n' +
          '<li>kafka</li>\n' +
          '<li>redis</li>\n' +
          '<li>打包成 docker 镜像<br />\n' +
          '<li>rpc<br />\n' +
          '……</li>\n' +
          '</ol>\n' +
          '<hr />'
      ;
     
      $(rust_web_content).insertBefore('.article-content');
    } else {
      console.log("Rust web 标签不存在");
    }
  });