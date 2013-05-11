# zPermissions &mdash; A Superperms plugin for Bukkit #

zPermissions is primarily an SQL database-backed Superperms (aka Bukkit permissions) implementation. It also supports flat-file storage. Notable features are: multi-world support, ranks with multiple tracks/ladders, group inheritance of arbitrary depth (within reason), and optional region-specific permissions using [WorldGuard](http://dev.bukkit.org/server-mods/worldguard/) regions or [Residence](http://dev.bukkit.org/server-mods/residence/) residences.

There is no build protection (I rely on WorldGuard for that). zPermissions focuses on permissions and only permissions.

I aim to keep zPermissions a simple, yet feature-rich, Superperms provider.

Please post bugs and/or feature requests as [dev.bukkit.org tickets](http://dev.bukkit.org/server-mods/zpermissions/tickets/).

[ [Quick start documentation](http://dev.bukkit.org/server-mods/zpermissions/pages/quick-start/) | [For Server Admins](http://dev.bukkit.org/server-mods/zpermissions/pages/for-server-admins/) | [FAQ](http://dev.bukkit.org/server-mods/zpermissions/pages/frequently-asked-questions/) ]

## Features ##

*   Uses Bukkit database to store permissions (i.e. settings in bukkit.yml). Should work with most databases supported by [Avaje Ebean](http://www.avaje.org) &mdash; I've specifically tested with PostgreSQL, MySQL, and H2. The default Bukkit database, SQLite, is **not** supported. zPermissions will automatically fall back to flat-file storage if it is used.

*   Group inheritance. Groups may inherit permissions from a single parent group.

*   Players may be members of more than one group. The order of which group permissions are applied is well defined and based on group weight (which is configurable, of course).

*   Multi-world support. Permissions granted to players and groups may be associated with a specific world.

*   Optional region support. Permissions may also be associated with WorldGuard regions or Residence residences.

*   Ranks! You may define multiple tracks on which to promote/demote users. Using permissions, you can also limit who can promote/demote others and which tracks they may use.

*   Short-term temporary permissions. Give a player a permission node that lasts anywhere from a few seconds to a few minutes.

*   Temporary group assignments. Assign a group to a player and have their membership expire after 1 day... a few months... or a year! Whatever duration you want.

*   With the advent of Superperms/Bukkit permissions, the recommended way of testing group membership is by using permissions. zPermissions can automatically set a permission based on the group's name for each group. By default, this configurable permission is `group.<groupname>` (compatible out-of-the-box with WorldEdit and WorldGuard!).

*   The default group (the group assigned to players who have not been explicitly placed into any groups) is named `default`. This may be changed.

## Concepts ##

*   Groups are "universal" &mdash; across all worlds. There are no plans to introduce world-specific groups.

*   However, players and groups may have world-specific and/or region-specific permissions. These permissions are only in effect when the player is in that particular world and/or region.

*   The most general permissions are applied first. So that means: universal group permissions, world-specific group permissions, region-specific universal permissions, then finally region-specific and world-specific permissions. Then repeat all that, but for player permissions.

*   Players may be members of multiple groups. Groups may be assigned a weight &mdash; a higher weight means the group is applied later so it overrides earlier groups. Groups with the same weight are applied alphabetically.

## Installation & Usage ##

Put zPermissions.jar in your server's `plugins` directory. Start up your server. This will create the file `config.yml` in your server's `plugins/zPermissions` directory. You may want to edit this file to set your default group and default track. You may also want to create your tracks.

Type `/permissions` to get started. (`/perm` or `/p` may also work, if those aliases are available.)

The permission nodes in the `get`, `set`, and `unset` sub-commands may be specified as:

*   &lt;permission> &mdash; An unqualified permission node applies to all worlds and all regions.
*   &lt;world>:&lt;permission> &mdash; To make a permission world-specific, prefix it with the world name followed by a colon.
*   &lt;region>/&lt;world>:&lt;permission> &mdash; Region-specific permissions take the above and prefix it with the region name followed by a forward-slash. For now, you may also omit the world name (so it applies to the named region in all worlds), though I'm not sure how useful this would be.

The rank commands are `/promote`, `/demote`, `/setrank`, and `/unsetrank` and will normally broadcast changes to all admins. The rank commands have an option `-q` to operate silently, e.g. when being called by automated processes. They will, however, still log their actions to the server log for security audit purposes. Opposite of `-q`, they will also take `-Q` which causes the rank commands to broadcast changes to all users.

## More Documentation ##

*   [Permissions](http://dev.bukkit.org/server-mods/zpermissions/pages/permissions)
*   [Detailed Command Usage](http://dev.bukkit.org/server-mods/zpermissions/pages/commands)
*   [Customizing Table Names](http://dev.bukkit.org/server-mods/zpermissions/pages/customizing-table-names/)
*   [Vault Support](http://dev.bukkit.org/server-mods/zpermissions/pages/vault-support)
*   [For Plugin Developers](http://dev.bukkit.org/server-mods/zpermissions/pages/for-plugin-developers/)
*   [Schema Updates](http://dev.bukkit.org/server-mods/zpermissions/pages/schema-updates/)

## License & Source ##

zPermissions is released under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).

Sources may be found on GitHub:

*   [zPermissions](https://github.com/ZerothAngel/zPermissions)
*   [ToHPluginUtils](https://github.com/ZerothAngel/ToHPluginUtils)

Development builds of this project can be acquired at the provided continuous integration server. 
These builds have not been approved by the BukkitDev staff. Use them at your own risk.

*   [zPermissions](http://ci.tyrannyofheaven.org/job/zPermissions/) (Requires ToHPluginUtils.jar)
*   [zPermissions-standlone](http://ci.tyrannyofheaven.org/job/zPermissions-standalone/) (includes ToHPluginUtils, like the version distributed on dev.bukkit.org)

## To Do ##

*   Commands to clone/copy the permissions for a user or group.
*   More extensive unit tests, especially on the service interface.
*   Better organized docs.
