package org.grails.plugin.hibernate.filter

import org.grails.datastore.mapping.model.PersistentEntity
import org.hibernate.MappingException
import org.hibernate.boot.spi.InFlightMetadataCollector
import org.hibernate.cfg.SecondPass

class HibernateFilterSecondPass implements SecondPass {

	def grailsDomainClassMappingContext
    protected InFlightMetadataCollector mappings

    HibernateFilterSecondPass(InFlightMetadataCollector mappings) {
        this.mappings = mappings
    }

	void doSecondPass(Map persistentClasses) throws MappingException {
		grailsDomainClassMappingContext.persistentEntities.each { PersistentEntity persistentEntity ->
			if (persistentEntity.hasProperty('hibernateFilters', Closure)) {
				new HibernateFilterBuilder(mappings, persistentEntity, persistentClasses.get(persistentEntity.name))
			}
		}
	}
}
