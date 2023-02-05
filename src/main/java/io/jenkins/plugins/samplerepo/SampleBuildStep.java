package io.jenkins.plugins.samplerepo;

import hudson.Extension;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;
import hudson.util.FormValidation;
import net.sf.json.JSONObject;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;

import java.io.IOException;
import java.net.URL;

public class SampleBuildStep extends Builder {

    private final String messageToDisplay;

    @DataBoundConstructor
    public SampleBuildStep(String messageToDisplay) {
        this.messageToDisplay = messageToDisplay;
    }

    public String getMessageToDisplay() {
        return messageToDisplay;
    }

    @Override
    public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener) throws InterruptedException, IOException {
        listener.getLogger().println(messageToDisplay);
        listener.getLogger().println(this.getDescriptor().getGlobalMessageToDisplay());
        return true;
    }

    @Override
    public DescriptorImpl getDescriptor() {
        return (DescriptorImpl) super.getDescriptor();
    }

    @Extension
    @Symbol("sample")
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {
        private String globalMessageToDisplay;

        public String getGlobalMessageToDisplay() {
            return globalMessageToDisplay;
        }

        public void setGlobalMessageToDisplay(String globalMessageToDisplay) {
            this.globalMessageToDisplay = globalMessageToDisplay;
        }

        // lgtm[jenkins/csrf]
        public FormValidation doCheckGlobalMessageToDisplay1(@QueryParameter String value) throws IOException {
            if (value.startsWith("http://")) {
                new URL(value).openConnection();
            }
            return FormValidation.ok();
        }

        @SuppressWarnings("lgtm[jenkins/csrf]")
        public FormValidation doCheckGlobalMessageToDisplay2(@QueryParameter String value) throws IOException {
            if (value.startsWith("http://")) {
                new URL(value).openConnection();
            }
            return FormValidation.ok();
        }

        // lgtm[jenkins/no-permission-check]
        @SuppressWarnings("lgtm[jenkins/csrf]")
        public FormValidation doCheckGlobalMessageToDisplay3(@QueryParameter String value) throws IOException {
            if (value.startsWith("http://")) {
                new URL(value).openConnection();
            }
            return FormValidation.ok();
        }

        public FormValidation doCheckGlobalMessageToDisplay4(@QueryParameter String value) throws IOException {
            if (value.startsWith("http://")) {
                new URL(value).openConnection();
            }
            return FormValidation.ok();
        }
        
        @Override
        public boolean isApplicable(Class<? extends AbstractProject> jobType) {
            return true;
        }

        @Override
        public boolean configure(StaplerRequest req, JSONObject data) throws FormException {
            req.bindJSON(this, data);
            return super.configure(req, data);
        }
    }
}
