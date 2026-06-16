# More Cobblemon Sound Events

Adds a collection of extra Cobblemon sound events, making it easier for both resourcepack and modpack creators to customise audio.

**Important:** This mod does **not** include any sound files by default. It simply registers additional sound events that can be mapped to your own sounds through a resource pack or other similar means.

## Usage

Create a `sounds.json` file at:

```text
assets/morecobblemonsoundevents/sounds.json
```

Then define the sound events you want to override -- For example:

```jsonc
// CoolResourcePack/assets/morecobblemonsoundevents/sounds.json
{
  "battle.pvw.win": {
    // Player vs Wild battle win event
    "sounds": [
      {
        "name": "mod:sound/id",
        "type": "event"
      }
    ]
  }
}
```

A complete list of available sound events can be found in the mod's bundled `sounds.json` file:

```text
src/main/resources/assets/morecobblemonsoundevents/sounds.json
```

### Referencing Sound Files

See [here](https://minecraft.fandom.com/wiki/Sounds.json) for more details on how to structure your `sounds.json` file and the available options.

#### Custom Sound Files

If you want to use custom sound files, make sure to place them in the correct location within your resource pack:

```text
assets/namespace/sounds/my-cool-win-sound.ogg
```

Then reference them in your `sounds.json`:

```jsonc
//..
"sounds": [
  {
    "name": "namespace:path/to/sound/file",
    "type": "file" // default is "file"
  }
]
//..
```

#### Existing Sounds

If you want to use existing sounds from other mods or Minecraft, you can reference them directly by their namespace and path:

```jsonc
//..
"sounds": [
  {
    "name": "entity.player.levelup",
    "type": "event"
  }
]
//..
```

You can also browse existing Minecraft sounds via <https://minecraftsounds.com>!

## License

Packaged under Apache License 2.0, see [LICENSE] for more details.

You may use this in your modpacks, all I ask is that you please link back to this [Modrinth Project](https://modrinth.com/project/zo9W5aiN) via a [manifest](https://support.modrinth.com/en/articles/8802351-modrinth-modpack-format-mrpack)!

<!--
  LINKS BELOW
-->

[LICENSE]: https://raw.githubusercontent.com/hudsonm62/MoreCobblemonSoundEvents/refs/heads/master/LICENSE
