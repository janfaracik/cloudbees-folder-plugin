package com.cloudbees.hudson.plugins.folder.actions;

import com.cloudbees.hudson.plugins.folder.computed.ComputedFolder;
import hudson.Extension;
import hudson.model.Action;
import jenkins.model.TransientActionFactory;
import jenkins.model.menu.Group;

import java.util.Collection;
import java.util.Set;

@Extension(ordinal = Integer.MAX_VALUE - 2)
public class EventsAction extends TransientActionFactory<ComputedFolder> {

    @Override
    public Class<ComputedFolder> type() {
        return ComputedFolder.class;
    }

    @Override
    public Collection<? extends Action> createFor(ComputedFolder target) {
        if (!target.isHasEvents()) {
            return Set.of();
        }

        return Set.of(new Action() {
            @Override
            public String getDisplayName() {
                return target.getPronoun() + " Events";
            }

            @Override
            public String getIconFileName() {
                return "symbol-pulse-outline plugin-ionicons-api";
            }

            @Override
            public Group getGroup() {
                return Group.of(4);
            }

            @Override
            public String getUrlName() {
                return target.getComputation().getUrl() + "events";
            }
        });
    }
}

