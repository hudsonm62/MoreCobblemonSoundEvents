# More Cobblemon Sound Events

More Cobblemon Sound Events adds a collection of extra Cobblemon sound events, making it easier for resource pack and modpack creators to customize audio.

**Important:** This mod does **not** include any sound files by default. It simply registers additional sound events that can be mapped to your own sounds through a resource pack.

## Usage

Create a `sounds.json` file at:

```text
assets/morecobblemonsoundevents/sounds.json
```

Then define the sound events you want to override or provide. For example:

```jsonc
// CoolResourcePack/assets/morecobblemonsoundevents/sounds.json
{
  "battle.pvw.win": { // Player vs Wild battle win event
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

### Important Notes

- All sound entries **must** use `"type": "event"`.
- These sound events do not correspond to actual sound files. You must provide your own sound files and map them to these events in your resource pack.

## License

This project is licensed under the **Apache License 2.0**. See the [LICENSE](LICENSE) file for full details.

### Modpack Usage

You are welcome to include this mod in modpacks. If you do so, please:

- Credit the original author (me!).
- Provide a link back to the mod's project page.
- Prefer linking to the mod through your modpack's manifest rather than redistributing the JAR directly.
