'use strict';

var app = angular.module('BaseApp',
    ['ngMaterial', 'ngMessages', 'material.svgAssetsCache', 'ngAnimate', 'chart.js', 'ui.bootstrap']);

app.config(function ($mdIconProvider, $mdThemingProvider, ChartJsProvider) {
    $mdIconProvider.defaultIconSet('/resources/icons/mdi.svg');

    $mdThemingProvider.definePalette('white', {
        '50': 'ffffff',
        '100': 'ffffff',
        '200': 'ffffff',
        '300': 'ffffff',
        '400': 'ffffff',
        '500': 'ffffff',
        '600': 'ffffff',
        '700': 'ffffff',
        '800': 'ffffff',
        '900': 'ffffff',
        'A100': 'ffffff',
        'A200': 'ffffff',
        'A400': 'ffffff',
        'A700': 'ffffff',
        'contrastDefaultColor': 'dark'
    });

    $mdThemingProvider.theme('default')
        .primaryPalette('white')
        .accentPalette('blue')
        .backgroundPalette('grey',
            {
                'default': '100'
            });

    $mdThemingProvider.enableBrowserColor({
        theme: 'default', // Default is 'default'
        palette: 'accent', // Default is 'primary', any basic material palette and extended palettes are available
        hue: '800' // Default is '800'
    });

    Chart.defaults.global.colors = ['#009688', '#9c27b0', '#f44336', '#795548', '#3F51B5', '#4CAF50', '#FF9800'];
});

app.controller('StatisticsController', ['$scope', function ($scope) {
    Chart.defaults.global.defaultFontColor = '#ffffff';
    Chart.defaults.global.showLines = false;
}]);

app.controller('SearchBarController', function ($scope) {
});