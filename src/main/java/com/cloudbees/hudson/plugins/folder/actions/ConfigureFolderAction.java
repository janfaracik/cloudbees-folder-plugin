package com.cloudbees.hudson.plugins.folder.actions;

import com.cloudbees.hudson.plugins.folder.AbstractFolder;
import com.cloudbees.hudson.plugins.folder.Folder;
import hudson.Extension;
import hudson.model.Action;
import jenkins.model.TransientActionFactory;
import jenkins.model.menu.Group;

import java.util.Collection;
import java.util.Set;

@Extension
public class ConfigureFolderAction extends TransientActionFactory<AbstractFolder> {

    @Override
    public Class<AbstractFolder> type() {
        return AbstractFolder.class;
    }

    @Override
    public Collection<? extends Action> createFor(AbstractFolder target) {
        if (!target.hasPermission(Folder.CONFIGURE)) {
            return Set.of();
        }

        return Set.of(new Action() {
            @Override
            public String getDisplayName() {
                return "Configure";
            }

            @Override
            public String getIconFileName() {
                return "symbol-settings";
            }

            @Override
            public Group getGroup() {
                return Group.FIRST_IN_MENU;
            }

            @Override
            public String getUrlName() {
                return "configure";
            }
        });
    }
}

