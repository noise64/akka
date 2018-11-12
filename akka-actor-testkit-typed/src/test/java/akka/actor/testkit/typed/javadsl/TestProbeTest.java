/*
 * Copyright (C) 2009-2018 Lightbend Inc. <https://www.lightbend.com>
 */

package akka.actor.testkit.typed.javadsl;

import akka.actor.typed.ActorRef;
import org.junit.ClassRule;
import org.junit.Test;
import org.scalatest.junit.JUnitSuite;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestProbeTest  extends JUnitSuite {

  @ClassRule
  public static TestKitJunitResource testKit = new TestKitJunitResource();

  @Test
  public void testAwaitAssert() {
    TestProbe<String> probe = TestProbe.create(testKit.system());
    probe.awaitAssert(() -> {
      // ... something ...
      return null;
    });
    probe.awaitAssert(Duration.ofSeconds(3), () -> {
      // ... something ...
      return null;
    });
    String awaitAssertResult =
      probe.awaitAssert(Duration.ofSeconds(3), Duration.ofMillis(100), () -> {
        // ... something ...
        return "some result";
      });
    assertEquals("some result", awaitAssertResult);
  }

  @Test
  public void testExpectMessage() {
    TestProbe<String> probe = TestProbe.create(testKit.system());
    probe.getRef().tell("message");
    String messageResult = probe.expectMessage("message");
    probe.getRef().tell("message2");
    String expectClassResult = probe.expectMessageClass(String.class);
    probe.expectNoMessage();
  }

  @Test
  public void testFish() {
    TestProbe<String> probe = TestProbe.create(testKit.system());
    probe.getRef().tell("one");
    probe.getRef().tell("one");
    probe.getRef().tell("two");
    List<String> results = probe.fishForMessage(Duration.ofSeconds(3), "hint", message -> {
      if (message.equals("one")) return FishingOutcomes.continueAndIgnore();
      else if (message.equals("two")) return FishingOutcomes.complete();
      else return FishingOutcomes.fail("error");
    });
    assertEquals(Arrays.asList("two"), results);
  }

  @Test
  public void testWithin() {
    TestProbe<String> probe = TestProbe.create(testKit.system());
    String withinResult = probe.within(Duration.ofSeconds(3), () -> {
      // ... something ...
      return "result";
    });
    assertEquals("result", withinResult);
  }

}
