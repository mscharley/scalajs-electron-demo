'use strict';

require('source-map-support').install();
require('../electron-scala-opt');

new App(__dirname, require('electron')).main();
