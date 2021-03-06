package com.epam.university.java.project.core.cdi.bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Implementation class for BeanDefinitionReader.
 *
 * @author Sergei Titov
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class BeanDefinitionImpl implements BeanDefinition {

    @XmlAttribute
    private String id;

    @XmlAttribute(name = "class")
    private String className;

    @XmlAttribute
    private String scope;

    @XmlAttribute
    private String postConstruct;

    @XmlElements({
            @XmlElement(type = BeanPropertyDefinitionImpl.class, name = "property")
    })
    private Collection<BeanPropertyDefinition> properties;

    /**
     * {@inheritDoc}
     */

    @Override
    public String getId() {
        return this.id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getClassName() {
        return this.className;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<BeanPropertyDefinition> getProperties() {

        return this.properties;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setProperties(Collection<BeanPropertyDefinition> properties) {
        this.properties = properties;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPostConstruct() {
        return this.postConstruct;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPostConstruct(String methodName) {
        this.postConstruct = methodName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getScope() {
        return this.scope;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setScope(String scope) {
        this.scope = scope;
    }
}
