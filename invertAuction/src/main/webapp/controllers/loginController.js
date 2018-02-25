// create the controller and inject Angular's $scope
invertAuctionApp.controller('loginController', function($scope, $http) {
    var loginParameters={
        'username' : $scope.username,
        'password' : $scope.password
    }

    $scope.submit = function () {

        $http.post('api/login', loginParameters).then(function (response) {
            console.log("login successful")
        }, function () {
            console.log("login failed")
        })

    }
});