package com.vacareanu.robert.trendinggithub

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class JobExecutor {

    companion object {

        private val INITIAL_POOL_SIZE = 3
        private val MAX_POOL_SIZE = 7
        private val IDLE_MAX_TIME: Long = 10
        private val IDLE_MAX_TIME_UNIT = TimeUnit.SECONDS

        private val workingQueue = LinkedBlockingQueue<Runnable>()
        private val threadPoolExecutor = ThreadPoolExecutor(INITIAL_POOL_SIZE, MAX_POOL_SIZE, IDLE_MAX_TIME, IDLE_MAX_TIME_UNIT, workingQueue, JobExecutorThreadFactory("_android"))

        fun execute(r: () -> Unit) {
            threadPoolExecutor.execute(r)
        }
    }

    class JobExecutorThreadFactory(private val THREAD_NAME: String) : ThreadFactory {
        var counter = 0
        override fun newThread(p0: Runnable?): Thread = Thread(p0, "$THREAD_NAME${counter++}")
    }
}

