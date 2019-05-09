/**
 * @file     main.c
 */
/* Embedded Xinu, Copyright (C) 2009, 2013.  All rights reserved. */

#include <xinu.h>

void gustave(void);

/**
 * Main thread.  You can modify this routine to customize what Embedded Xinu
 * does when it starts up.  The default is designed to do something reasonable
 * on all platforms based on the devices and features configured.
 */
thread main(void)
{
    /* Print information about the operating system  */

    kprintf("Hello Xinu World!\r\n");

    gustave();

    while (1)
        ;
    return 0;
}
