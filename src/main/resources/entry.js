'use strict';

require('source-map-support').install();
require('../electron-scala-opt');

console.log("Starting electron-scala...");
electronscala.App(__dirname, require('electron')).main();
