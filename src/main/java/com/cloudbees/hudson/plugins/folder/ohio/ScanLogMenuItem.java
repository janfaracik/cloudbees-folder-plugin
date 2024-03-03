package com.cloudbees.hudson.plugins.folder.ohio;

import com.cloudbees.hudson.plugins.folder.computed.ComputedFolder;
import hudson.Extension;
import hudson.model.Action;
import hudson.model.View;
import jenkins.model.TransientActionFactory;
import jenkins.model.menu.Group;
import jenkins.model.menu.event.LinkAction;

import java.util.Collection;
import java.util.Set;

@Extension
public class ScanLogMenuItem extends TransientActionFactory<View> {

    @Override
    public Class<View> type() {
        return View.class;
    }

    @Override
    public Collection<? extends Action> createFor(View target) {
        if (!(target.getOwner() instanceof ComputedFolder)) {
            return Set.of();
        }

        return Set.of(
                new Action() {
                    @Override
                    public String getDisplayName() {
                        return "Scan now";
                    }

                    @Override
                    public String getIconFileName() {
                        return "symbol-play";
                    }

                    @Override
                    public Group getGroup() {
                        return Group.FIRST_IN_MENU;
                    }

                    @Override
                    public jenkins.model.menu.event.Action getAction() {
                        return LinkAction.of("build?delay=0");
                    }
                },
                new Action() {
                    @Override
                    public String getDisplayName() {
                        return "Organisation Folder Events";
                    }

                    @Override
                    public String getIconFileName() {
                        return "symbol-pulse-outline plugin-ionicons-api";
                    }

                    @Override
                    public Group getGroup() {
                        return Group.FIRST_IN_MENU;
                    }

                    @Override
                    public jenkins.model.menu.event.Action getAction() {
                        return LinkAction.of("computation/events");
                    }
                },
                new Action() {
                    @Override
                    public String getDisplayName() {
                        return "Scan Organisation Folder Log";
                    }

                    @Override
                    public String getIconFileName() {
                        return "symbol-terminal";
                    }

                    @Override
                    public Group getGroup() {
                        return Group.FIRST_IN_MENU;
                    }

                    @Override
                    public jenkins.model.menu.event.Action getAction() {
                        return LinkAction.of("computation/console");
                    }
                }
        );
    }
}
