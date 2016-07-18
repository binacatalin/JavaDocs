'use strict';

hrApp.factory('CommonResourcesFactory', function() {
        // var baseUrl = "http://hrapp-zth.rhcloud.com/hrapp/";
        var baseUrl = "http://localhost:8090/app/mvc/";
        return {
            findAllDepartmentsUrl: baseUrl + "departments",
            findAllEmployeesUrl: baseUrl + "employees",
            findAllJobsUrl: baseUrl + "jobs" + "/all",
            findAllLocationsUrl: baseUrl + "locations",
            findOneDepartmentUrl: baseUrl + "departments/",
            findOneEmployeeUrl: baseUrl + "employees/",
            findOneJobUrl: baseUrl + "jobs/" + "one",
            findOneLocationUrl: baseUrl + "locations/",
            deleteDepartmentUrl: baseUrl + "departments",
            deleteEmployeeUrl: baseUrl + "employees",
            deleteJobUrl: baseUrl + "jobs" + "/delete",
            deleteLocationUrl: baseUrl + "locations",
            addDepartmentUrl: baseUrl + "departments",
            addEmployeeUrl: baseUrl + "employees",
            addJobUrl: baseUrl + "jobs" + "/save",
            addLocationUrl: baseUrl + "locations",
            editDepartmentUrl: baseUrl + "departments",
            editEmployeeUrl: baseUrl + "employees",
            editJobUrl: baseUrl + "jobs" + "/edit",
            editLocationUrl: baseUrl + "locations"
        };
    }
);