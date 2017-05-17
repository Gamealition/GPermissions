**GPermissions** is a Bukkit plugin that manages permissions. It is an implementation of Superperms.

GPermissions is a downstream fork of [zPermissions][ZPERMS], with compatiability fixes and some
features removed.

For information on how to use GPermissions, [see the original zPermissions page.][WIKI]

# Differences from upstream

Most of the differences are feature removals. We do not use these features.

* Removed database support (used Ebeans ORM, removed in Spigot 1.12)
* Removed Factions, Factoid and Residence region support
* Shaded in ToHPluginUtils source

# Support

This fork is not intended to be a long-term continuation of zPermissions; this is only for our
fixes, to keep zPermissions working. We may remove features that we don't use or think should be 
provided by zPermissions.

That said; please feel free to cherry pick commits or fork from this fork, or contribute to this
fork with issues and pull requests.

If you are starting a new server, you may wish to try [LuckPerms][LUCK] instead.

# Building, debugging and debug logging

For instructions and screenshots on how to. . .

* Compile this plugin from scratch
* Build a JAR of this plugin
* Debug this plugin on a server
* Enable debug logging levels such as `FINE` and `FINER`

. . .[please follow the linked guide on this Google document.][BUILD]

## License

As GPermissions is a fork of zPermissions by ZerothAngel, GPermissions is licensed the under the
Apache 2.0 license. Please see `LICENSE` or [this website][LICENSE] for the full license.

[ZPERMS]: https://github.com/CoolV1994/zPermissions/
[WIKI]: https://www.spigotmc.org/resources/zpermissions.11736/
[BUILD]: https://docs.google.com/document/d/1TTDXG7IZ9M0D2-rzbILAWg1CKjynHK8fNGxbf3W4wBk/view
[LICENSE]: https://www.apache.org/licenses/LICENSE-2.0
[LUCK]: https://github.com/lucko/LuckPerms