<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="hasRole('ROLE_SYS_ADMIN')">
    <script type="text/javascript">
        app.controller('SideNavCtrl', function ($scope) {
            $scope.menuItems = [
                {'icon': 'home', 'title': 'Моя сторінка'},
                {'icon': 'account-multiple', 'title': 'Students'},
                {'icon': 'file-document', 'title': 'Tests'},
                {'icon': 'star', 'title': 'Starred'},
                {},
                {'icon': 'archive', 'title': 'Archive'},
                {},
                {'icon': 'settings', 'title': 'Налаштування системи Futoria'},
                {'icon': 'settings', 'title': 'Налаштування модулю Futoria Tests'},
                {}
            ];
        });
    </script>

    <md-sidenav
            class="md-sidenav-left"
            md-component-id="left"
            style="margin-top: 30px; max-width: 250px"
            md-colors="{background: 'background'}"
            md-is-locked-open="$mdMedia('gt-md')">
        <md-list flex
                 ng-controller="SideNavCtrl">
            <div ng-repeat="item in menuItems">
                <md-list-item ng-click="null"
                              style="min-height: 40px; max-height: 40px"
                              ng-if="item.title != null">
                    <md-icon md-svg-icon="{{item.icon}}"
                             style="margin-right: 24px; margin-top: 8px; margin-bottom: 6px"></md-icon>
                    <p class="md-caption"
                       ng-style="{'color': '#424242'}"> {{item.title}} </p>
                </md-list-item>

                <md-divider ng-if="item.title == null"
                            style="margin-top: 8px; margin-bottom:8px;"></md-divider>
            </div>
        </md-list>
    </md-sidenav>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_UNI_ADMIN')" >
    <script type="text/javascript">
        app.controller('SideNavCtrl', function ($scope) {
            $scope.menuItems = [
                {'icon': 'home', 'title': 'Моя сторінка'},
                {'icon': 'account-multiple', 'title': 'Students'},
                {'icon': 'file-document', 'title': 'Tests'},
                {'icon': 'star', 'title': 'Starred'},
                {},
                {'icon': 'archive', 'title': 'Archive'},
                {},
                {'icon': 'domain', 'title': 'Мій університет'},
                {'icon': 'account-multiple', 'title': 'Мої студенти'},
                {}
            ];
        });
    </script>

    <md-sidenav
            class="md-sidenav-left"
            md-component-id="left"
            style="margin-top: 30px; max-width: 250px"
            md-colors="{background: 'background'}"
            md-is-locked-open="$mdMedia('gt-md')">
        <md-list flex
                 ng-controller="SideNavCtrl">
            <div ng-repeat="item in menuItems">
                <md-list-item ng-click="null"
                              style="min-height: 40px; max-height: 40px"
                              ng-if="item.title != null">
                    <md-icon md-svg-icon="{{item.icon}}"
                             style="margin-right: 24px; margin-top: 8px; margin-bottom: 6px"></md-icon>
                    <p class="md-caption"
                       ng-style="{'color': '#424242'}"> {{item.title}} </p>
                </md-list-item>

                <md-divider ng-if="item.title == null"
                            style="margin-top: 8px; margin-bottom:8px;"></md-divider>
            </div>
        </md-list>
    </md-sidenav>
</sec:authorize>