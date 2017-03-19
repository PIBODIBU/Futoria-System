<!DOCTYPE HTML>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page session="true" %>

<html>
<head>
    <jsp:include page="include/angular-lib.jsp"/>
    <jsp:include page="include/angular-app.jsp"/>
</head>

<body ng-app="BaseApp" ng-cloak>

<md-content ng-controller="AppCtrl" md-colors="background">
    <%--Toolbars--%>
    <md-content md-whiteframe="4" style="z-index: 999">
        <md-toolbar>
            <div class="md-toolbar-tools">
                <div flex layout="row" layout-align="start center">
                    <span class="md-headline" style="color: #2196F3">F</span>
                    <span class="md-headline" style="color: #F44336">u</span>
                    <span class="md-headline" style="color: #4CAF50">t</span>
                    <span class="md-headline" style="color: #FFC107">o</span>
                    <span class="md-headline" style="color: #4CAF50">r</span>
                    <span class="md-headline" style="color: #2196F3">i</span>
                    <span class="md-headline" style="color: #F44336">a</span>
                    <span>&nbsp;</span>
                    <span class="md-title"
                          style="color: #757575; font-family: RobotoLight; font-weight: bolder">Tests</span>
                </div>

                <%--<h2 flex md-truncate> Welcome, ${pageContext.request.userPrincipal.name}</h2>--%>
            </div>
        </md-toolbar>

        <md-toolbar>
            <div class="md-toolbar-tools">
                <md-button class="md-raised md-accent">New test</md-button>

                <md-button class="md-icon-button" aria-label="More">
                    <md-icon md-svg-icon="done"></md-icon>
                </md-button>
            </div>
        </md-toolbar>
    </md-content>

    <%--Main content--%>
    <section layout="row" flex>
        <%--Side navigation--%>
        <md-sidenav
                flex="20"
                class="md-sidenav-left"
                md-component-id="left"
                style="padding-right: 24px"
                md-colors="{background: 'background'}"
                md-is-locked-open="$mdMedia('gt-md')">
            <div ng-controller="SideNavCtrl">
                <md-list flex>
                    <md-list-item ng-click="null"
                                  ng-repeat="item in menuItems">
                        <md-icon md-svg-icon="{{item.icon}}"></md-icon>
                        <p class="md-body-1" ng-style="{'color': '#424242'}"> {{item.title}} </p>
                    </md-list-item>

                    <md-divider></md-divider>

                    <md-list-item ng-click="null"
                                  ng-repeat="item in menuItems">
                        <md-icon md-svg-icon="{{item.icon}}"></md-icon>
                        <p class="md-body-1" ng-style="{'color': '#424242'}"> {{item.title}} </p>
                    </md-list-item>
                </md-list>

                <md-button ng-click="close()" class="md-primary" hide-gt-md>
                    Close Sidenav Left
                </md-button>
            </div>
        </md-sidenav>

        <%--Content--%>
        <div flex
             layout="row"
             layout-wrap
             layout-align="center">
            <md-list flex="50">
                <md-subheader class="md-no-sticky">Roles</md-subheader>
                <md-list-item class="md-2-line" ng-repeat="role in myRoles" ng-click="null">
                    <div class="md-list-item-text" layout="column">
                        <h4>{{ role.name }}</h4>
                    </div>
                </md-list-item>
            </md-list>

            <md-list flex="50">
                <md-subheader class="md-no-sticky">Permissions</md-subheader>
                <md-list-item class="md-2-line" ng-repeat="perm in myPermissions" ng-click="null">
                    <div class="md-list-item-text" layout="column">
                        <h4>{{ perm.name }}</h4>
                    </div>
                </md-list-item>
            </md-list>
        </div>
    </section>
</md-content>

<script type="text/javascript">
    app.controller('SideNavCtrl', function ($scope) {
        $scope.menuItems = [
            {'icon': 'account', 'title': 'My page'},
            {'icon': 'account-multiple', 'title': 'Students'},
            {'icon': 'file-document', 'title': 'Tests'},
            {'icon': 'star', 'title': 'Starred'},
            {'icon': 'archive', 'title': 'Archive'}
        ];
    });

    app.controller('AppCtrl', function ($scope) {
        $scope.myRoles = ${roles};
        $scope.myPermissions = ${my_permissions};
        $scope.borodaPermissions = ${other_permissions};
    });

    app.controller('LeftCtrl', function ($scope, $timeout, $mdSidenav, $log) {
        $scope.close = function () {
            // Component lookup should always be available since we are not using `ng-if`
            $mdSidenav('left').close()
                .then(function () {
                    $log.debug("close LEFT is done");
                });

        };
    })
</script>
</body>
</html>