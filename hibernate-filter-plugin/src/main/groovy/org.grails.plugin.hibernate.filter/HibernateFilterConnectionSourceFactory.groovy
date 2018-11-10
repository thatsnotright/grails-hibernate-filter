package org.grails.plugin.hibernate.filter

import groovy.transform.CompileStatic
import org.grails.orm.hibernate.connections.HibernateConnectionSourceFactory

/**
 * Created by akramer on 12/6/16.
 */
@CompileStatic
class HibernateFilterConnectionSourceFactory extends HibernateConnectionSourceFactory {

    HibernateFilterConnectionSourceFactory(Class...classes) {
        super(classes)
        this.metadataContributor = new HibernateFilterBinder(mappingContext)
    }
}
