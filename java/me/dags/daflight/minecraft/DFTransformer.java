package me.dags.daflight.minecraft;

import net.minecraft.launchwrapper.IClassTransformer;

/**
 * @author dags_ <dags@dags.me>
 */

public abstract class DFTransformer implements IClassTransformer
{
    @Override
    public final byte[] transform(String className, String transformedName, byte[] classBytes)
    {
        boolean isObf = !transformedName.startsWith("net.minecraft");
        return matches(transformedName,isObf) ? transform(transformedName, classBytes, isObf) : classBytes;
    }

    public abstract boolean matches(String transformedName, boolean isObf);

    public abstract byte[] transform(String transformedName, byte[] classBytes, boolean isObf);
}
