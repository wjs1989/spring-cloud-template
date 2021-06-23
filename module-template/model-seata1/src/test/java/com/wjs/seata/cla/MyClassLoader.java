package com.wjs.seata.cla;

import lombok.SneakyThrows;
import org.springframework.asm.Attribute;
import org.springframework.asm.ClassReader;
import org.springframework.cglib.core.ClassGenerator;
import org.springframework.cglib.core.CodeGenerationException;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.transform.AbstractClassLoader;
import org.springframework.cglib.transform.ClassReaderGenerator;

import java.io.IOException;
import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;

/**
 * @author wenjs
 */
public class MyClassLoader extends ClassLoader{
    private static ProtectionDomain DOMAIN = (ProtectionDomain) AccessController.doPrivileged(new PrivilegedAction() {
        public Object run() {
            return AbstractClassLoader.class.getProtectionDomain();
        }
    });

    @SneakyThrows
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = null;

                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();
                    c = find(name);
                }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }

    private Class find(String name) throws ClassNotFoundException, IOException {
        ClassReader r = null;
            InputStream is = this.getClass().getResourceAsStream(name.replace('.', '/') + ".class");
            if (is == null) {
                throw new ClassNotFoundException(name);
            }

            try {
                r = new ClassReader(is);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                is.close();
            }

        try {
            DebuggingClassWriter w = new DebuggingClassWriter(2);
            this.getGenerator(r).generateClass(w);
            byte[] b = w.toByteArray();
            Class c = super.defineClass(name, b, 0, b.length, DOMAIN);
            this.postProcess(c);
            return c;
        } catch (RuntimeException var12) {
            throw var12;
        } catch (Error var13) {
            throw var13;
        } catch (Exception var14) {
            throw new CodeGenerationException(var14);
        }
    }
    protected ClassGenerator getGenerator(ClassReader r) {
        return new ClassReaderGenerator(r, this.attributes(), this.getFlags());
    }

    protected int getFlags() {
        return 0;
    }

    protected Attribute[] attributes() {
        return null;
    }
    protected void postProcess(Class c) {
    }
}
