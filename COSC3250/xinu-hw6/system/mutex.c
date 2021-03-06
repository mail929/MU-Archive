/**
 * @file mutex.c
 * @provides mutexInit, mutexAcquire, mutexRelease
 *
 */

#include <xinu.h>

static volatile bool waiting[NPROC];
static volatile bool lock;
/**
 * Initialize bounded-waiting mutex subsystem.
 */
void mutexInit(void)
{
    int i = 0;
    for (i = 0; i < NPROC; i++)
        waiting[i] = FALSE;
    lock = FALSE;
}

/**
 * Acquire lock using MIPS testAndSet.
 * See textbook Figure 5.7, page 211.
 */
void mutexAcquire(void)
{
    bool key = TRUE;
    waiting[currpid] = TRUE;
    while(waiting[currpid] && key)
    {
        key = testAndSet(&lock);
    }
    waiting[currpid] = FALSE;
}

/**
 * Release acquired lock, fairly select next proc to enter critical section.
 * See textbook Figure 5.7, page 211.
 */
void mutexRelease(void)
{
    int j;
    j = (currpid + 1) % NPROC;
    // TODO:
    // Implement mutex release and select the next process
    //   to admit into its critical section.
    while((j != currpid) && !waiting[j])
    {
        j = (j + 1) % NPROC;
    }
    
    if(currpid == j)
    {
        lock = FALSE;
    }
    else
    {
        waiting[j] = FALSE;
    }
}
