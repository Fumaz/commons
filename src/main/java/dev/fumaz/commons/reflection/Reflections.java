package dev.fumaz.commons.reflection;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * A utility to make reflection easier and safer
 *
 * @author Fumaz
 * @version 3.1
 * @since 1.0
 */
@SuppressWarnings({"UnstableApiUsage", "unchecked"})
public final class Reflections {

    private Reflections() {
    }

    /**
     * Constructs an object from its class and arguments
     *
     * @param clazz      the class of the object
     * @param parameters the arguments for the constructor
     * @param <T>        the type of the object
     * @return the constructed object
     */
    public static <T> T construct(Class<T> clazz, Object... parameters) {
        try {
            Class<?>[] parameterTypes = Arrays.stream(parameters)
                    .map(Object::getClass)
                    .toArray(Class<?>[]::new);

            Constructor<T> constructor = Reflections.getSuitableConstructor(clazz, parameterTypes, parameters);

            if (constructor == null) {
                throw new NoSuchMethodException("Couldn't find constructor for given parameters");
            }

            constructor.setAccessible(true);
            return constructor.newInstance(parameters);
        } catch (InvocationTargetException | NoSuchMethodException e) {
            throw new ReflectionException("Exception whilst fetching the method", e);
        } catch (IllegalAccessException | InstantiationException e) {
            throw new ReflectionException("Exception whilst instantiating the object", e);
        }
    }

    /**
     * Constructs an object from its class and arguments types and objects
     *
     * @param clazz     the class of the object
     * @param arguments a map of arguments for the constructor
     * @param <T>       the type of the object
     * @return the constructed object
     */
    public static <T> T construct(Class<T> clazz, Map<Class<?>, Object> arguments) {
        try {
            Class<?>[] parameterTypes = arguments.keySet().toArray(new Class<?>[0]);
            Object[] parameters = arguments.values().toArray();

            Constructor<T> constructor = Reflections.getSuitableConstructor(clazz, parameterTypes, parameters);

            if (constructor == null) {
                throw new NoSuchMethodException("Couldn't find constructor for given parameters");
            }

            constructor.setAccessible(true);
            return constructor.newInstance(parameters);
        } catch (InvocationTargetException | NoSuchMethodException e) {
            throw new ReflectionException("Exception whilst fetching the method", e);
        } catch (IllegalAccessException | InstantiationException e) {
            throw new ReflectionException("Exception whilst instantiating the object", e);
        }
    }

    /**
     * Constructs an object from its class (with no constructor arguments)
     *
     * @param clazz the class of the object
     * @param <T>   the type of the object
     * @return the constructed object
     */
    public static <T> T construct(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            throw new ReflectionException("Exception whilst instantiating the object", e);
        }
    }

    /**
     * Returns all classes inside a package
     *
     * @param classLoader the classloader to use
     * @param pkg         the package's full path
     * @param recursive   whether to fetch classes in subpackages
     * @return the fetched classes
     */
    public static Set<Class<?>> getClassesInPackage(ClassLoader classLoader, String pkg, boolean recursive) {
        try {
            ClassPath path = ClassPath.from(classLoader);
            ImmutableSet<ClassPath.ClassInfo> classes = recursive ? path.getTopLevelClassesRecursive(pkg) : path.getTopLevelClasses(pkg);

            return classes.stream()
                    .map(ClassPath.ClassInfo::load)
                    .collect(Collectors.toSet());
        } catch (Exception e) {
            throw new ReflectionException("Exception whilst fetching classes", e);
        }
    }

    /**
     * Returns all classes inside a package that are subclasses of a super class
     *
     * @param classLoader the classloader to use
     * @param pkg         the package's full path
     * @param type        the type of the super class
     * @param recursive   whether to fetch classes in subpackages
     * @param <T>         the type of the super class
     * @return the fetched classes
     */
    public static <T> Set<Class<? extends T>> getMatchingClassesInPackage(ClassLoader classLoader, String pkg, Class<T> type, boolean recursive) {
        Set<Class<?>> classes = Reflections.getClassesInPackage(classLoader, pkg, recursive);

        return classes.stream()
                .filter(type::isAssignableFrom)
                .map(clazz -> (Class<? extends T>) clazz)
                .collect(Collectors.toSet());
    }

    /**
     * Returns all classes inside a package that are concrete and are subclasses of a super class
     *
     * @param classLoader the classloader to use
     * @param pkg         the package's full path
     * @param type        the type of the super class
     * @param recursive   whether to fetch classes in subpackages
     * @param <T>         the type of the super class
     * @return the fetched classes
     */
    public static <T> Set<Class<? extends T>> getConcreteClassesInPackage(ClassLoader classLoader, String pkg, Class<T> type, boolean recursive) {
        Set<Class<? extends T>> classes = Reflections.getMatchingClassesInPackage(classLoader, pkg, type, recursive);

        return classes.stream()
                .filter(clazz -> !Modifier.isAbstract(clazz.getModifiers()))
                .collect(Collectors.toSet());
    }

    /**
     * Gets all classes inside a package and passes them to a consumer
     *
     * @param classLoader the classloader to use
     * @param pkg         the package's full path
     * @param recursive   whether to fetch classes in subpackages
     * @param consumer    the consumer the classes will be passed to
     */
    public static void consume(ClassLoader classLoader, String pkg, boolean recursive, Consumer<Class<?>> consumer) {
        Reflections.getClassesInPackage(classLoader, pkg, recursive).forEach(consumer);
    }

    /**
     * Gets all classes inside a package that are subclasses of the super class and passes them to a consumer
     *
     * @param classLoader the classloader to use
     * @param pkg         the package's full path
     * @param type        the type of the super class
     * @param recursive   whether to fetch classes in subpackages
     * @param consumer    the consumer the classes will be passed to
     * @param <T>         the type of the super class
     */
    public static <T> void consume(ClassLoader classLoader, String pkg, Class<T> type, boolean recursive, Consumer<Class<? extends T>> consumer) {
        Reflections.getMatchingClassesInPackage(classLoader, pkg, type, recursive).forEach(consumer);
    }

    /**
     * Gets all classes inside a package that are concrete and subclasses of the super class and passes them to a consumer
     *
     * @param classLoader the classloader to use
     * @param pkg         the package's full path
     * @param type        the type of the super class
     * @param recursive   whether to fetch classes in subpackages
     * @param consumer    the consumer the classes will be passed to
     * @param <T>         the type of the super class
     */
    public static <T> void consumeConcrete(ClassLoader classLoader, String pkg, Class<T> type, boolean recursive, Consumer<Class<? extends T>> consumer) {
        Reflections.getConcreteClassesInPackage(classLoader, pkg, type, recursive).forEach(consumer);
    }

    /**
     * Gets all classes inside a package that are subclasses of the super class and passes a new instance of them to a consumer
     *
     * @param classLoader the classloader to use
     * @param pkg         the package's full path
     * @param type        the type of the super class
     * @param recursive   whether to fetch classes in subpackages
     * @param consumer    the consumer the instances will be passed to
     * @param <T>         the type of the super class
     */
    public static <T> void consumeInstance(ClassLoader classLoader, String pkg, Class<T> type, boolean recursive, Consumer<T> consumer) {
        Set<Class<? extends T>> classes = Reflections.getConcreteClassesInPackage(classLoader, pkg, type, recursive);

        classes.forEach(clazz -> consumer.accept(Reflections.construct(clazz)));
    }

    /**
     * Gets all classes inside a package that are subclasses of the super class and passes a new instance of them to a consumer with the defined arguments
     *
     * @param classLoader the classloader to use
     * @param pkg         the package's full path
     * @param type        the type of the super class
     * @param recursive   whether to fetch classes in subpackages
     * @param consumer    the consumer the instances will be passed to
     * @param args        the arguments for the constructors of the classes
     * @param <T>         the type of the super class
     */
    public static <T> void consumeInstance(ClassLoader classLoader, String pkg, Class<T> type, boolean recursive, Consumer<T> consumer, Object... args) {
        Set<Class<? extends T>> classes = Reflections.getConcreteClassesInPackage(classLoader, pkg, type, recursive);

        classes.forEach(clazz -> consumer.accept(Reflections.construct(clazz, args)));
    }

    /**
     * Returns a field from the specified class
     *
     * @param clazz the class
     * @param name  the name of the field
     * @return the field
     */
    public static Field getField(Class<?> clazz, String name) {
        try {
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);

            return field;
        } catch (NoSuchFieldException e) {
            throw new ReflectionException("Exception whilst getting the field", e);
        }
    }

    /**
     * Returns a field from the specified object
     *
     * @param object the object
     * @param name   the name of the field
     * @return the field
     */
    public static Field getField(Object object, String name) {
        return getField(object.getClass(), name);
    }

    /**
     * Returns the value of a field from the specified object
     *
     * @param object the object to retrieve the field from
     * @param field  the field
     * @param <T>    the type of the field
     * @return the field's value
     */
    public static <T> T getFieldValue(Object object, Field field) {
        try {
            return (T) field.get(object);
        } catch (IllegalAccessException e) {
            throw new ReflectionException("Exception whilst getting the field's value", e);
        }
    }

    /**
     * Returns the value of a field from the specified object
     *
     * @param object the object to retrieve the field from
     * @param name   the name of the field
     * @param <T>    the type of the field
     * @return the field's value
     */
    public static <T> T getFieldValue(Object object, String name) {
        Field field = Reflections.getField(object, name);

        return getFieldValue(object, field);
    }

    /**
     * Sets the value of the field of a specified object
     *
     * @param object the object
     * @param field  the field
     * @param value  the value to assign
     */
    public static void setField(Object object, Field field, Object value) {
        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            throw new ReflectionException("Exception whilst setting the value of the field", e);
        }
    }

    /**
     * Sets the value of the field of a specified object
     *
     * @param object the object
     * @param name   the name of the field
     * @param value  the value to assign
     */
    public static void setField(Object object, String name, Object value) {
        Field field = getField(object, name);

        setField(object, field, value);
    }

    /**
     * Gets a method from the specified class
     *
     * @param clazz the class
     * @param name  the name of the method
     * @return the method
     */
    public static Method getMethod(Class<?> clazz, String name) {
        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    /**
     * Gets a method from the specified object's class
     *
     * @param instance the object
     * @param name     the name of the method
     * @return the method
     */
    public static Method getMethod(Object instance, String name) {
        return getMethod(instance.getClass(), name);
    }

    /**
     * Invokes an object's method
     *
     * @param instance  the object
     * @param method    the method to invoke
     * @param arguments the arguments to pass to the method
     */
    public static void invokeMethod(Object instance, Method method, Object... arguments) {
        try {
            method.invoke(instance, arguments);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new ReflectionException("Exception whilst invoking the method", e);
        }
    }

    /**
     * Invokes an object's method
     *
     * @param instance  the object
     * @param name      the name of the method to invoke
     * @param arguments the arguments to pass to the method
     */
    public static void invokeMethod(Object instance, String name, Object... arguments) {
        Method method = getMethod(instance, name);
        invokeMethod(instance, method, arguments);
    }

    private static <T> Constructor<T> getSuitableConstructor(Class<T> clazz, Class<?>[] parameterTypes, Object[] parameters) {
        for (Constructor<?> declared : clazz.getDeclaredConstructors()) {
            if (!checkParameters(declared, parameterTypes)) {
                continue;
            }

            return (Constructor<T>) declared;
        }

        return null;
    }

    private static boolean checkParameters(Executable executable, Class<?>[] parameterTypes) {
        Class<?>[] executableParameterTypes = executable.getParameterTypes();

        // TODO: Add check for varargs
        if (executableParameterTypes.length != parameterTypes.length) {
            return false;
        }

        for (int i = 0; i < parameterTypes.length; i++) {
            if (!parameterTypes[i].isAssignableFrom(executableParameterTypes[i])) {
                return false;
            }
        }

        return true;
    }

}
