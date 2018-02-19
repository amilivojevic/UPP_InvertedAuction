// create the controller and inject Angular's $scope
invertAuctionApp.controller('registrationController', function($scope, $http) {
    // create a message to display in our view
    $scope.message = 'Everyone come and see how good I look!';

    $scope.categories = ["cleaning", "building", "procurement", "other..."];

    $scope.submit = function () {
        //VALIDACIJA!!!

        console.log("userUsername: " + $scope.userUsername);

        var props = {
            name : $scope.userName,
            mail : $scope.userMail,
            username: $scope.userUsername
        }

        $http.get('api/start-process').then(function (response) {
            console.log("RESPONSE: " + response);
        }, function () {
            console.log("FAILED");
        });

        $http.post('api/start-process', props).then(function (response) {
            console.log("RESPONSE: " + response);

            if($scope.userType === "company"){
                /*
               $http.get('api/'+$scope.userUsername+'/get-agent-formkey').then(function(response){
                    console.log("fort key = " + JSON.stringify(response));
                }, function(){})
                */

                var agentProps = {
                    name : $scope.agentName,
                    mail : $scope.agentMail,
                    username: $scope.agentUsername
                }
                $http.post('api/'+$scope.userUsername+'/register-agent',agentProps).then(function (response) {

                },function () {
                })

            }

        }, function () {
            console.log("FAILED");
        });

    }
});