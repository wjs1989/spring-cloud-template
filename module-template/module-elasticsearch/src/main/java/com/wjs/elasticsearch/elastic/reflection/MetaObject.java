// package com.wjs.elasticsearch.elastic.reflection;
//
// import org.springframework.beans.BeanWrapper;
//
// import java.util.Collection;
// import java.util.List;
// import java.util.Map;
//
// /**
//  * @author wenjs
//  */
// public class MetaObject {
//     private final Object originalObject;
//     private final ObjectWrapper objectWrapper;
//     private final ObjectFactory objectFactory;
//     private final ObjectWrapperFactory objectWrapperFactory;
//     private final ReflectorFactory reflectorFactory;
//
//     private MetaObject(Object object, ObjectFactory objectFactory, ObjectWrapperFactory objectWrapperFactory, ReflectorFactory reflectorFactory) {
//         this.originalObject = object;
//         this.objectFactory = objectFactory;
//         this.objectWrapperFactory = objectWrapperFactory;
//         this.reflectorFactory = reflectorFactory;
//         if (object instanceof ObjectWrapper) {
//             this.objectWrapper = (ObjectWrapper)object;
//         } else if (objectWrapperFactory.hasWrapperFor(object)) {
//             this.objectWrapper = objectWrapperFactory.getWrapperFor(this, object);
//         } else if (object instanceof Map) {
//             this.objectWrapper = new MapWrapper(this, (Map)object);
//         } else if (object instanceof Collection) {
//             this.objectWrapper = new CollectionWrapper(this, (Collection)object);
//         } else {
//             this.objectWrapper = new BeanWrapper(this, object);
//         }
//
//     }
//
//     public static MetaObject forObject(Object object, ObjectFactory objectFactory, ObjectWrapperFactory objectWrapperFactory, ReflectorFactory reflectorFactory) {
//         return object == null ? SystemMetaObject.NULL_META_OBJECT : new MetaObject(object, objectFactory, objectWrapperFactory, reflectorFactory);
//     }
//
//     public ObjectFactory getObjectFactory() {
//         return this.objectFactory;
//     }
//
//     public ObjectWrapperFactory getObjectWrapperFactory() {
//         return this.objectWrapperFactory;
//     }
//
//     public ReflectorFactory getReflectorFactory() {
//         return this.reflectorFactory;
//     }
//
//     public Object getOriginalObject() {
//         return this.originalObject;
//     }
//
//     public String findProperty(String propName, boolean useCamelCaseMapping) {
//         return this.objectWrapper.findProperty(propName, useCamelCaseMapping);
//     }
//
//     public String[] getGetterNames() {
//         return this.objectWrapper.getGetterNames();
//     }
//
//     public String[] getSetterNames() {
//         return this.objectWrapper.getSetterNames();
//     }
//
//     public Class<?> getSetterType(String name) {
//         return this.objectWrapper.getSetterType(name);
//     }
//
//     public Class<?> getGetterType(String name) {
//         return this.objectWrapper.getGetterType(name);
//     }
//
//     public boolean hasSetter(String name) {
//         return this.objectWrapper.hasSetter(name);
//     }
//
//     public boolean hasGetter(String name) {
//         return this.objectWrapper.hasGetter(name);
//     }
//
//     public Object getValue(String name) {
//         PropertyTokenizer prop = new PropertyTokenizer(name);
//         if (prop.hasNext()) {
//             MetaObject metaValue = this.metaObjectForProperty(prop.getIndexedName());
//             return metaValue == SystemMetaObject.NULL_META_OBJECT ? null : metaValue.getValue(prop.getChildren());
//         } else {
//             return this.objectWrapper.get(prop);
//         }
//     }
//
//     public void setValue(String name, Object value) {
//         PropertyTokenizer prop = new PropertyTokenizer(name);
//         if (prop.hasNext()) {
//             MetaObject metaValue = this.metaObjectForProperty(prop.getIndexedName());
//             if (metaValue == SystemMetaObject.NULL_META_OBJECT) {
//                 if (value == null) {
//                     return;
//                 }
//
//                 metaValue = this.objectWrapper.instantiatePropertyValue(name, prop, this.objectFactory);
//             }
//
//             metaValue.setValue(prop.getChildren(), value);
//         } else {
//             this.objectWrapper.set(prop, value);
//         }
//
//     }
//
//     public MetaObject metaObjectForProperty(String name) {
//         Object value = this.getValue(name);
//         return forObject(value, this.objectFactory, this.objectWrapperFactory, this.reflectorFactory);
//     }
//
//     public ObjectWrapper getObjectWrapper() {
//         return this.objectWrapper;
//     }
//
//     public boolean isCollection() {
//         return this.objectWrapper.isCollection();
//     }
//
//     public void add(Object element) {
//         this.objectWrapper.add(element);
//     }
//
//     public <E> void addAll(List<E> list) {
//         this.objectWrapper.addAll(list);
//     }
//
// }
