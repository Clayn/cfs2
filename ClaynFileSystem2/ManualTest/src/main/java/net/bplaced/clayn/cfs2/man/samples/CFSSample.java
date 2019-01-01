package net.bplaced.clayn.cfs2.man.samples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.1
 */
public abstract class CFSSample
{

    protected final Logger log;

    public CFSSample()
    {
        log = LoggerFactory.getLogger(getClass());
    }

    public abstract void executeSample() throws Exception;
}
