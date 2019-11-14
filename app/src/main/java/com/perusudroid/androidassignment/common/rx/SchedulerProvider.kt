package com.perusudroid.androidassignment.common.rx

import io.reactivex.Scheduler

interface SchedulerProvider {

     fun computation(): Scheduler

     fun io(): Scheduler

     fun ui(): Scheduler

}