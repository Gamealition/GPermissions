package org.tyrannyofheaven.bukkit.zPermissions.util.uuid;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

public class UuidTest {

    @Test
    public void testConversion() {
        UUID uuid = UUID.randomUUID();
        Assert.assertEquals(uuid.toString(), UuidUtils.shortUuidToLong(UuidUtils.longUuidToShort(uuid.toString())));
    }

    @Test
    public void testNoMatch() {
        UuidDisplayName udn = UuidUtils.parseUuidDisplayName("ZerothAngel");
        assertNull(udn);
    }

    @Test
    public void testLongUuidMatchNoName() {
        UUID uuid = UUID.randomUUID();
        UuidDisplayName udn = UuidUtils.parseUuidDisplayName(uuid.toString());
        assertNotNull(udn);
        assertEquals(uuid, udn.getUuid());
        assertNull(udn.getDisplayName());
    }

    @Test
    public void testShortUuidMatchNoName() {
        UUID uuid = UUID.randomUUID();
        UuidDisplayName udn = UuidUtils.parseUuidDisplayName(UuidUtils.longUuidToShort(uuid.toString()));
        assertNotNull(udn);
        assertEquals(uuid, udn.getUuid());
        assertNull(udn.getDisplayName());
    }

    @Test
    public void testLongUuidMatch() {
        UUID uuid = UUID.randomUUID();
        UuidDisplayName udn = UuidUtils.parseUuidDisplayName(uuid.toString() + "/ZerothAngel");
        assertNotNull(udn);
        assertEquals(uuid, udn.getUuid());
        assertEquals("ZerothAngel", udn.getDisplayName());
    }

    @Test
    public void testShortUuidMatch() {
        UUID uuid = UUID.randomUUID();
        UuidDisplayName udn = UuidUtils.parseUuidDisplayName(UuidUtils.longUuidToShort(uuid.toString()) + "/ZerothAngel");
        assertNotNull(udn);
        assertEquals(uuid, udn.getUuid());
        assertEquals("ZerothAngel", udn.getDisplayName());
    }

}
