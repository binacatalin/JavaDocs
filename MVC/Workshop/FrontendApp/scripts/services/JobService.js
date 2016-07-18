'use strict';

hrApp.service('JobService', ['$http', 'CommonResourcesFactory', function($http, CommonResourcesFactory) {
    return {
        findAll: function() {
            return $http.get(CommonResourcesFactory.findAllJobsUrl)
                .success(function(data) {
                    return data;
                }).error(function(data) {
                    return data;
            });
        },
        findOne: function(jobId) {
            // return $http.get(CommonResourcesFactory.findOneJobUrl + jobId)
            return $http.get(CommonResourcesFactory.findOneJobUrl, {params: {id: jobId}})
                .success(function(data) {
                    return data;
                }).error(function(data) {
                    return data;
                });
        },
        add: function(job) {
            return $http.post(CommonResourcesFactory.addJobUrl, job)
            // return $http.post(CommonResourcesFactory.addJobUrl, {params: {id: job}})
                .success(function(data) {
                    return data;
                })
                .error(function(data) {
                    return data;
                });
        },
        edit: function(job) {
            return $http.put(CommonResourcesFactory.editJobUrl, job)
            // return $http.put(CommonResourcesFactory.editJobUrl, {params: {id: jobId}})
                .success(function(data) {
                    return data;
                })
                .error(function(data) {
                    return data;
                });
        },
        delete: function(jobId) {
            // return $http.delete(CommonResourcesFactory.deleteJobUrl + '/' + jobId)
            return $http.delete(CommonResourcesFactory.deleteJobUrl, {params: {id: jobId}})
                .success(function(data) {
                    return data;
                })
                .error(function(data) {
                    return data;
                });
        }
    }
}]);