package com.cloudbees.hudson.plugins.folder.actions;

import com.cloudbees.hudson.plugins.folder.computed.ComputedFolder;
import com.cloudbees.hudson.plugins.folder.computed.FolderComputation;
import hudson.Extension;
import hudson.model.Action;
import hudson.model.View;
import jenkins.model.TransientActionFactory;
import jenkins.model.menu.Group;

import java.util.Collection;
import java.util.Set;

@Extension(ordinal = Integer.MAX_VALUE - 1)
public class ComputeLogAction extends TransientActionFactory<ComputedFolder> {

    @Override
    public Class<ComputedFolder> type() {
        return ComputedFolder.class;
    }

    @Override
    public Collection<? extends Action> createFor(ComputedFolder target) {
        FolderComputation<?> computation = target.getComputation();

        return Set.of(new Action() {
            @Override
            public String getDisplayName() {
                return computation.getDisplayName() + " Log";
            }

            @Override
            public String getIconFileName() {
                return "symbol-terminal";
            }

            @Override
            public Group getGroup() {
                return Group.of(4);
            }

            @Override
            public String getUrlName() {
                return computation.getUrl() + "console";
            }
        });
    }
}

