// create the controller and inject Angular's $scope
invertAuctionApp.controller('loginController', function($scope, $http,$window,$location,LoginFactory) {

    var vm = this;

    vm.loggedIn = false;
    // login and logout methods

    checkIfLogged();

    function checkIfLogged(){

        if($window.localStorage.getItem("token")){
            console.log("CheckedIfLogged: Logged");
            vm.loggedIn = true;
            vm.token = $window.localStorage.getItem("token");

            var promise = LoginFactory.getLoggedUserData(vm.token);
            promise.then(
                function(loggedUser) {

                    $window.localStorage['loggedUser'] = angular.toJson(loggedUser);
                    console.log("ucitan u funkciji window.localstorage: " + JSON.stringify($window.localStorage['loggedUser']));
                    $scope.loggedUser = loggedUser;
                    var user = angular.fromJson($window.localStorage['loggedUser']);

                    $window.location = "#!/main";
                }
            );


        }
        else{
            vm.loggedIn = false;
        }
        console.log("loggedin = " + vm.loggedIn);
    }


    $scope.login = function(){
        var loginParameters={
            'username' : $scope.username,
            'password' : $scope.password
        }
        console.log("LOGIN");

        $http.post('api/login', loginParameters).then(function (token) {
            console.log("login successful, response: " + token.data);

            var t = token.data.split(" ")[0];
            var role = token.data.split(" ")[1];

            $window.localStorage.setItem("token",t);
            console.log("token = " + $window.localStorage.getItem("token"));
            console.log("role = " + role);


        }, function () {
            alert(response.data.response);
            console.log("login failed");
        })

    }

    $scope.logout = function() {
        console.log("usao u logout");
        $window.localStorage.removeItem("token");
        $window.localStorage.removeItem("loggedUser");
        checkIfLogged();
        $location.path('/');
    }
});