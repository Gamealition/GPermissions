/*
 * Copyright 2011 ZerothAngel <zerothangel@tyrannyofheaven.org>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tyrannyofheaven.bukkit.zPermissions.model;

import org.tyrannyofheaven.bukkit.zPermissions.util.ToHUtils;
import org.tyrannyofheaven.bukkit.zPermissions.util.uuid.UuidUtils;

import java.util.*;

/**
 * The permission entity &mdash; something that can have a set of permission
 * entries. Originally I had different classes for players and groups, but
 * Avaje's handling of inheritance seems a bit wonky. So I've basically
 * collapsed both classes into this single class, using {@link #isGroup()} as
 * a discriminator. Ugh...
 * 
 * @author zerothangel
 */
public class PermissionEntity {

    private Long id;

    private String name;

    private boolean group;

    private String displayName;

    private int priority;

    private PermissionEntity parent;
    
    private Set<Entry> permissions = new HashSet<>();

    private Set<Membership> memberships = new HashSet<>();

    private Set<Inheritance> inheritancesAsParent = new HashSet<>();
    
    private Set<Inheritance> inheritancesAsChild = new HashSet<>();

    private Set<EntityMetadata> metadata = new HashSet<>();

    private final Map<String, EntityMetadata> metadataMap = new HashMap<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGroup() {
        return group;
    }

    public void setGroup(boolean group) {
        this.group = group;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public PermissionEntity getParent() {
        return parent;
    }

    public void setParent(PermissionEntity parent) {
        this.parent = parent;
    }

    public Set<Entry> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Entry> permissions) {
        this.permissions = permissions;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Set<Membership> getMemberships() {
        return memberships;
    }

    public void setMemberships(Set<Membership> memberships) {
        this.memberships = memberships;
    }

    public Set<Inheritance> getInheritancesAsParent() {
        return inheritancesAsParent;
    }

    public void setInheritancesAsParent(Set<Inheritance> inheritancesAsParent) {
        this.inheritancesAsParent = inheritancesAsParent;
    }

    public Set<Inheritance> getInheritancesAsChild() {
        return inheritancesAsChild;
    }

    public void setInheritancesAsChild(Set<Inheritance> inheritancesAsChild) {
        this.inheritancesAsChild = inheritancesAsChild;
    }

    public List<PermissionEntity> getParents() {
        List<Inheritance> inheritances = new ArrayList<>(getInheritancesAsChild());
        Collections.sort(inheritances);
        List<PermissionEntity> result = new ArrayList<>(inheritances.size());
        for (Inheritance i : inheritances)
            result.add(i.getParent());
        return result;
    }

    public Set<PermissionEntity> getChildrenNew() {
        Set<PermissionEntity> result = new LinkedHashSet<>(getInheritancesAsParent().size());
        for (Inheritance i : getInheritancesAsParent())
            result.add(i.getChild());
        return result;
    }

    public Set<EntityMetadata> getMetadata() {
        return metadata;
    }

    public void setMetadata(Set<EntityMetadata> metadata) {
        this.metadata = metadata;
    }

    public Map<String, EntityMetadata> getMetadataMap() {
        return metadataMap;
    }

    public void updateMetadataMap() {
        getMetadataMap().clear();
        for (EntityMetadata em : getMetadata()) {
            getMetadataMap().put(em.getName().toLowerCase(), em);
        }
    }

    public UUID getUuid() {
        ToHUtils.assertFalse(isGroup(), "Only valid for players");
        return UuidUtils.uncanonicalizeUuid(getName());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof PermissionEntity)) return false;
        PermissionEntity o = (PermissionEntity)obj;
        return getName().equals(o.getName()) &&
            isGroup() == o.isGroup();
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + getName().hashCode();
        result = 37 * result + Boolean.valueOf(isGroup()).hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("Entity[%s,%s,%s]", getName(), getDisplayName(), isGroup());
    }

}
