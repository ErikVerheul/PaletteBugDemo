# PaletteBugDemo

No arrow images are rendered despite the  palette.add(new DefaultTheme()); code line in Homepage.java.
This demo works fine with Wicket 6.20.0 and the above code line commented out.

Problem FIXED in the previous commit thanks to the help of martin-g on Stackoverflow. His remarks:

The p paragraph cannot contain other block elements. In Wicket 6.x the palette uses a table to layout the "Available" und "Selected" options. 
In Wicket 7.x divs are used for that, since tables only should be used for tabular data and not for layouting. Neither tables nor divs are 
allowed in a paragraph though. I guess browsers are just a bit more forgiving when using tables at wrong places, hence you are seeing a 
somewhat correct result with version 6.x.

Another problem is that you are using a span tag for your palette. Again, the palette is inserting either a table or several div tags, 
but neither of these are valid in between the span tag.

So something like this should do it:

<form>
  <div class="mystyle">
    <div wicket:id="palette"></div>
  </div>
  ...
</form>

