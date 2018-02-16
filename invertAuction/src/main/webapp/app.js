// create the module and name it scotchApp
var invertAuctionApp = angular.module('invertAuctionApp', ['ngRoute']);

// configure our routes
invertAuctionApp.config(function($routeProvider) {
    $routeProvider

    // route for the home page
        .when('/', {
            templateUrl : '/pages/main.html',
            controller  : 'mainController'
        })
        .when('/register', {
            templateUrl : '/pages/registration.html',
            controller  : 'registrationController'
        })
        .otherwise({
            redirectTo: '/'
        });
});