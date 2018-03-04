invertAuctionApp.controller('userProfileController', function($scope, $http,$window,$location,LoginFactory, $route) {

    $scope.index = -1;

    $http.get("/api/job-categories/all").then(function (response) {

        console.log("JOB CATEGORIES: " + JSON.stringify(response.data));
        $scope.jobCategories = response.data;
        $scope.userTasks = [];

        $scope.loggedUser = angular.fromJson($window.localStorage['loggedUser']);
        console.log("vm.userData = " + JSON.stringify($scope.loggedUser.id));


        //HARDKODOVAN AGENT1!!!
        $http.get("/api/activiti/getUserTasks/agent1").then(function (tasks) {
            console.log("TASKS: " + JSON.stringify(tasks.data));
            $scope.userTasks = tasks.data;
            //console.log("$scope.usertasks = " + JSON.stringify($scope.userTasks));

        }, function () {
            console.log("Failed to retrieve user tasks");
        })

    },function () {
        console.log("Failed to retrieve job categoories");
    });

    $scope.logout = function() {
        console.log("usao u logout");
        $window.localStorage.removeItem("token");
        $window.localStorage.removeItem("loggedUser");
        //$location.path('#/login');
        return true;
    }
    
    $scope.startNewIA = function () {

        var index = $scope.jobCategories.indexOf($scope.job_category) + 1;
        var newProcProcesProps={
            'job_category' : index,
            'job_description' : $scope.job_description,
            'job_max_price' : $scope.job_max_price,
            'job_application_deadline' : $scope.job_application_deadline,
            'job_min_candidates' :$scope.job_min_candidates,
            'job_max_candidates' :$scope.job_max_candidates,
            'job_deadline' : $scope.job_deadline

        }

        console.log("new process props: " + JSON.stringify(newProcProcesProps));
        $http.post("/api/start-ia",newProcProcesProps).then(function () {
           console.log("start-ia successfull");
            $window.location.reload();
        }, function () {
            console.log("start-ia failed");
        });
    }

    $scope.openUserTask = function (index) {
        var t = $scope.userTasks[index];
        $scope.props = [];
        $scope.index = index;
        $http.get("/api/activiti/getUserTasksProperties/" + t.id).then(function (props) {
            //console.log("  *Props for task " + t.id + ", " + t.name);
            //console.log(JSON.stringify(props.data));
            $scope.props = props.data;
            $scope.propVals = []
            var prop;
            for (i = 0; i < $scope.props.length; i++) {
                prop = $scope.props[i];
                $scope.propVals.push({
                    "id" : prop.id
                });
            }

            //console.log("$scope.props: " + JSON.stringify($scope.props));
        }, function () {
            console.log("Failed to retrieve properties for: " + t.id);
        })
    }

    $scope.submitTaskProps = function () {
        //console.log("propVals = " + JSON.stringify($scope.propVals));
        var taskName = $scope.userTasks[$scope.index].name;
        var taskId = $scope.userTasks[$scope.index].id;
        console.log("TaskId = " + taskId);
        var map = {};
        var propId;
        var propValue;
        for (i = 0; i < $scope.propVals.length; i++) {
            propId = $scope.propVals[i].id.toString();
            propValue = $scope.propVals[i].value.toString();
            map[propId] = propValue;
        }
        console.log("Props: " + JSON.stringify(map));


        switch(taskName){
            case "Client confirmation":{
                $http.post("/api/client/" + taskId + "/not-enough-candidates-confirmation", map).then(function () {
                    console.log("notEnoughCandidatesConfirmation successful!");
                    $window.location.reload();
                },function () {
                    console.log("notEnoughCandidatesConfirmation failed");
                })
            }

        }

    }

});