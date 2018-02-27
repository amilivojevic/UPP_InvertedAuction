// create the module and name it scotchApp
var invertAuctionApp = angular.module('invertAuctionApp', ['ngRoute']);

// configure our routes
invertAuctionApp.config(function($routeProvider,$httpProvider) {
    $routeProvider

    // route for the home page
        .when('/', {
            templateUrl : '/pages/main.html',
            controller  : 'mainController'
        })
        .when('/main', {
            templateUrl : '/pages/main.html',
            controller  : 'mainController'
        })
        .when('/register', {
            templateUrl : '/pages/registration.html',
            controller  : 'registrationController'
        })
        .when('/login', {
            templateUrl : '/pages/login.html',
            controller  : 'loginController'
        })
        .when('/logout', {
            templateUrl : '/pages/logout.html',
            controller  : 'logoutController'
        })
        .when('/userProfile', {
            templateUrl : '/pages/userProfile.html',
            controller  : 'userProfileController'
        })
        .otherwise({
            redirectTo: '/'
        });


    $httpProvider
        .interceptors.push(['$q', '$window',
        function($q, $window) {
            return {
                'request': function(config) {
                    config.headers = config.headers || {};
                    if($window.localStorage.token) {
                        config.headers["X-Auth-Token"] = $window.localStorage.token;
                    }
                    return config;
                },
                'responseError': function(response) {
                    if (response.status === 401 || response.status === 403) {

                        console.log("nesto kod interseptora je pogresno");
                    }
                    return $q.reject(response);
                }
            };
        }
    ]);
});