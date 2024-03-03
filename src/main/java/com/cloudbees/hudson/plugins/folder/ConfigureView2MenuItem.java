package com.cloudbees.hudson.plugins.folder;

import hudson.Extension;
import hudson.model.Action;
import hudson.model.AllView;
import hudson.model.View;
import jenkins.model.TransientActionFactory;
import jenkins.model.menu.Group;
import jenkins.model.menu.event.DropdownAction;
import jenkins.model.menu.event.LinkAction;

import java.util.Collection;
import java.util.Set;

@Extension
public class ConfigureView2MenuItem extends TransientActionFactory<View> {

    @Override
    public Class<View> type() {
        return View.class;
    }

    @Override
    public Collection<? extends Action> createFor(View target) {
        if (!(target.getOwner() instanceof AbstractFolder)) {
            return Set.of();
        }

        if (target instanceof AllView) {
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
                    return Group.IN_APP_BAR;
                }

                @Override
                public jenkins.model.menu.event.Action getAction() {
                    return LinkAction.of("configure");
                }
            });
        }

        return Set.of(
                new Action() {
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
                        return Group.IN_APP_BAR;
                    }

                    @Override
                    public jenkins.model.menu.event.Action getAction() {
                        return DropdownAction.of(
                                new Action() {
                                    @Override
                                    public String getIconFileName() {
                                        return "symbol-settings";
                                    }

                                    @Override
                                    public String getDisplayName() {
                                        return "Edit view";
                                    }

                                    @Override
                                    public jenkins.model.menu.event.Action getAction() {
                                        return LinkAction.of("configure");
                                    }
                                },
                                new Action() {
                                    @Override
                                    public String getIconFileName() {
                                        return "symbol-settings";
                                    }

                                    @Override
                                    public String getDisplayName() {
                                        return "Edit folder";
                                    }

                                    @Override
                                    public jenkins.model.menu.event.Action getAction() {
                                        return LinkAction.of("../../configure");
                                    }
                                }
                        );
                    }
                }
        );
    }
}
