'use strict';

(function() {
  require('source-map-support').install();
  var appName = process.env['npm_package_name'];
  try {
    require('./' + appName + '-opt');
  }
  catch (e) {
    require('./' + appName + '-fastopt');
  }

  ElectronQuickStart.App(__dirname, require).main();
})();
