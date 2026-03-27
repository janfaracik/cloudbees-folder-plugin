package com.cloudbees.hudson.plugins.folder.actions;

import com.cloudbees.hudson.plugins.folder.computed.ComputedFolder;
import hudson.Extension;
import hudson.model.Action;
import hudson.model.Item;
import java.util.Collection;
import java.util.Set;
import jenkins.model.TransientActionFactory;
import jenkins.model.menu.Group;
import jenkins.model.menu.event.Event;
import jenkins.model.menu.event.LinkEvent;

@Extension
public class ComputeNowAction extends TransientActionFactory<ComputedFolder> {

    @Override
    public Class<ComputedFolder> type() {
        return ComputedFolder.class;
    }

    @Override
    public Collection<? extends Action> createFor(ComputedFolder target) {
        if (!(target.hasPermission(Item.BUILD) && target.isBuildable())) {
            return Set.of();
        }

        return Set.of(new Action() {
            @Override
            public String getDisplayName() {
                return target.getComputation().getDisplayName() + " Now";
            }

            @Override
            public String getIconFileName() {
                return "symbol-play";
            }

            @Override
            public Group getGroup() {
                return Group.of(4);
            }

            @Override
            public String getUrlName() {
                return "build?delay=0";
            }

            @Override
            public Event getEvent() {
                return LinkEvent.of(getUrlName(), LinkEvent.LinkEventType.POST);
            }
        });
    }
}

