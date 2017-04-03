<!DOCTYPE HTML>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page session="true" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <jsp:include page="include/angular-lib.jsp"/>
    <jsp:include page="include/angular-app.jsp"/>
</head>

<style>
    .avatar {
        width: 100%;
        height: auto;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        cursor: pointer;
    }

    .image-cropper-small {
        width: 36px;
        height: 36px;
        position: relative;
        overflow: hidden;
        border-radius: 50%;
    }

    .image-cropper-middle {
        width: 48px;
        height: 48px;
        min-height: 48px;
        min-width: 48px;
        position: relative;
        overflow: hidden;
        border-radius: 50%;
    }

    .image-cropper-large {
        height: 56px;
        min-height: 70px;
        width: 56px;
        min-width: 70px;
        position: relative;
        overflow: hidden;
        border-radius: 50%;
    }

    .image-cropper-large-bordered {
        height: 56px;
        min-height: 70px;
        width: 56px;
        min-width: 70px;
        position: relative;
        overflow: hidden;
        border-radius: 50%;
        border: solid white;
    }

    .card-wrapper {
        padding: 8px;
    }

    .card-wrapper > md-card {
        margin: 0
    }
</style>

<body ng-app="BaseApp"
      ng-cloak>

<div layout="column"
     flex
     layout-fill
     ng-cloak
     ng-controller="AppCtrl as ctrl"
     md-colors="background">

    <%--Toolbar--%>
    <jsp:include page="template/toolbar.jsp"/>

    <%--Main content--%>
    <div layout="row"
         layout-fill
         flex>
        <%--Side navigation--%>
        <div layout="column">
            <jsp:include page="template/sidenav.jsp"/>
        </div>

        <%--Content--%>
        <md-content layout="row"
                    flex
                    layout-align="space-between start"
                    layout-wrap>
            <%--Test list--%>
            <div flex="50"
                 flex-xs="100"
                 ng-repeat="test in tests"
                 class="card-wrapper">
                <md-card>
                    <md-card-title>
                        <md-card-title-text>
                            <span class="md-headline">{{test.title}}</span>
                        </md-card-title-text>
                    </md-card-title>

                    <md-divider></md-divider>

                    <md-card-content>
                        UUID: {{test.uuid}}
                        <br/>
                        Предмет: {{test.subject.longName}}

                        <md-list flex
                                 class="md-dense">
                            <md-list-item ng-click="null"
                                          ng-repeat="question in test.questions"
                                          class="md-2-line">
                                <div class="md-list-item-text">
                                    <h3>{{question.title}}</h3>
                                    <p>{{question.difficulty.level}}-й рівень складності</p>
                                </div>
                            </md-list-item>
                        </md-list>
                    </md-card-content>
                </md-card>
            </div>
        </md-content>
    </div>
</div>

<script type="text/javascript">
    app.controller('AppCtrl', function ($scope) {
        $scope.tests = ${tests};

        this.openMenu = function ($mdMenu, ev) {
            $mdMenu.open(ev);
        };
    });
</script>
</body>
</html>