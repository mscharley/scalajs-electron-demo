'use strict';

(function() {
  require('source-map-support').install();
  var appName = 'scalajs-electron-quick-start';
  try {
    require('./' + appName + '-opt');
  }
  catch (e) {
    require('./' + appName + '-fastopt');
  }

  ElectronQuickStart.App(__dirname, require).main();
})();
