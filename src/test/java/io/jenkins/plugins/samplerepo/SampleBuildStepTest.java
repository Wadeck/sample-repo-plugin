package io.jenkins.plugins.samplerepo;

import hudson.model.FreeStyleBuild;
import hudson.model.FreeStyleProject;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

import java.nio.charset.StandardCharsets;

public class SampleBuildStepTest {
    @Rule
    public JenkinsRule j = new JenkinsRule();

    @Test
    public void localMessageTest() throws Exception {
        String message = "Sample step done";

        FreeStyleProject project = j.createFreeStyleProject();
        project.getBuildersList().add(new SampleBuildStep(message));

        FreeStyleBuild b = j.buildAndAssertSuccess(project);

        Assert.assertTrue(IOUtils.toString(b.getLogInputStream(), StandardCharsets.UTF_8).contains(message));
    }

    @Test
    public void multipleMessageTest() throws Exception {
        String message1 = "message1";
        String message2 = "message2";

        FreeStyleProject project = j.createFreeStyleProject();
        project.getBuildersList().add(new SampleBuildStep(message1));
        project.getBuildersList().add(new SampleBuildStep(message2));

        FreeStyleBuild b = j.buildAndAssertSuccess(project);

        String buildLog = IOUtils.toString(b.getLogInputStream(), StandardCharsets.UTF_8);
        Assert.assertTrue(buildLog.contains(message1));
        Assert.assertTrue(buildLog.contains(message2));
    }

    @Test
    public void localAndGlobalMessageTest() throws Exception {
        String localMessage = "Sample step done";
        String globalMessage = "Global message";

        SampleBuildStep.DescriptorImpl descriptor = j.jenkins.getDescriptorByType(SampleBuildStep.DescriptorImpl.class);
        descriptor.setGlobalMessageToDisplay(globalMessage);

        FreeStyleProject project = j.createFreeStyleProject();
        project.getBuildersList().add(new SampleBuildStep(localMessage));

        FreeStyleBuild b = j.buildAndAssertSuccess(project);

        String buildLog = IOUtils.toString(b.getLogInputStream(), StandardCharsets.UTF_8);
        Assert.assertTrue(buildLog.contains(localMessage));
        Assert.assertTrue(buildLog.contains(globalMessage));
    }
}
