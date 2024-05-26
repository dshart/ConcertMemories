 populateDropdown(displayRoles) {
        const select = document.getElementById('userRoles');
        for (const displayRole of displayRoles) {
                var opt = document.createElement('option');
                opt.innerText = displayRole.orgDisplayName.concat(" ~ ", displayRole.jobRole);
                opt.setAttribute('id', displayRole.orgDisplayName)
                if (displayRole.roleStatus == 'Pending') {
                    opt.innerText = displayRole.orgDisplayName.concat(" ~ ", displayRole.jobRole).concat("  PENDING APPROVAL");
                    opt.disabled = 'disabled';
                }
                select.appendChild(opt);
        }
        document.getElementById('userRoles').hidden = false;
    }

    changeButtonTarget() {
        var selectedOrg = document.getElementById('userRoles').options[document.getElementById('userRoles').selectedIndex].id
        const button = document.getElementById('navigate-btn');
        for (var role of this.dataStore.get(DISPLAY_ROLES_KEY)) {
            if (role.orgDisplayName === selectedOrg) {
                if(role.jobRole == 'Manager') {
                    button.setAttribute('href', 'projectsList.html?orgId=' + role.orgId);
                }

                if(role.jobRole == 'Worker') {
                    button.setAttribute('href', 'assignedTaskList.html?orgId=' + role.orgId);
                }
            }
        }

