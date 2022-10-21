package me.mryakar.sds.scheduler;

import net.javacrumbs.shedlock.core.LockAssert;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "5m")
public class ScheduledJob {

    @Scheduled(
            initialDelayString = "${delay.initial}",
            fixedDelayString = "${delay.fixed}"
    )
    @SchedulerLock(name = "print-calendar-time-job-lock")
    public void scheduledJob() throws InterruptedException {
        LockAssert.assertLocked();
        System.out.println("Calendar time: " + Calendar.getInstance().getTime());
        Thread.sleep(10000);
    }
}