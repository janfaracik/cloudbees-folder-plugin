package com.cloudbees.hudson.plugins.folder;

import hudson.Extension;
import hudson.model.Action;
import hudson.model.AllView;
import hudson.model.View;
import jenkins.model.TransientActionFactory;
import jenkins.model.menu.Group;
import jenkins.model.menu.Semantic;
import jenkins.model.menu.event.DropdownAction;
import jenkins.model.menu.event.LinkAction;

import java.util.Collection;
import java.util.Set;

@Extension
public class DeleteView2MenuItem extends TransientActionFactory<View> {

    @Override
    public Class<View> type() {
        return View.class;
    }

    @Override
    public Collection<? extends Action> createFor(View target) {
        if (!(target.getOwner() instanceof Folder)) {
            return Set.of();
        }

        if (target instanceof AllView) {
            return Set.of(new Action() {
                @Override
                public String getDisplayName() {
                    return "Delete folder";
                }

                @Override
                public String getIconFileName() {
                    return "symbol-trash";
                }

                @Override
                public Group getGroup() {
                    return Group.LAST_IN_MENU;
                }

                @Override
                public jenkins.model.menu.event.Action getAction() {
                    return LinkAction.of("delete");
                }

                @Override
                public Semantic getSemantic() {
                    return Semantic.DESTRUCTIVE;
                }
            });
        }

        return Set.of(
                new Action() {
                    @Override
                    public String getDisplayName() {
                        return "Delete";
                    }

                    @Override
                    public String getIconFileName() {
                        return "symbol-trash";
                    }

                    @Override
                    public Group getGroup() {
                        return Group.LAST_IN_MENU;
                    }

                    @Override
                    public Semantic getSemantic() {
                        return Semantic.DESTRUCTIVE;
                    }

                    @Override
                    public jenkins.model.menu.event.Action getAction() {
                        return DropdownAction.of(
                                new Action() {
                                    @Override
                                    public String getIconFileName() {
                                        return "symbol-trash";
                                    }

                                    @Override
                                    public String getDisplayName() {
                                        return "Delete view";
                                    }

                                    @Override
                                    public jenkins.model.menu.event.Action getAction() {
                                        return LinkAction.of("delete");
                                    }

                                    @Override
                                    public Semantic getSemantic() {
                                        return Semantic.DESTRUCTIVE;
                                    }
                                },
                                new Action() {
                                    @Override
                                    public String getIconFileName() {
                                        return "symbol-trash";
                                    }

                                    @Override
                                    public String getDisplayName() {
                                        return "Delete folder";
                                    }

                                    @Override
                                    public jenkins.model.menu.event.Action getAction() {
                                        return LinkAction.of("../../delete");
                                    }

                                    @Override
                                    public Semantic getSemantic() {
                                        return Semantic.DESTRUCTIVE;
                                    }
                                }
                        );
                    }
                }
        );
    }
}
