# LZW (Lempel–Ziv–Welch) compression algorithm

A [LZW](https://en.wikipedia.org/wiki/LZ77_and_LZ78) (Lempel–Ziv–Welch) compression algorithm implementation using java. It was an assignment for the Compression and Information theory course in Cairo University Faculty of Computers and Artificial Intelligence.  
This implementation can't compress binary files, it is not designed to, so only text ASCII files can be compressed.

## How to use

To compress a file run the program with the following arguments:
```shell
java -jar lzw.jar -c <filename>
```

For decompression:
```shell
java -jar lzw.jar -d <filename>
```

## Example

Using 5 paragraphs of [Lorem ipsum](https://www.lipsum.com/feed/html):  
[Input file](examples/lipsum.txt) is 2,588 bytes  
[Compressed file](examples/lipsum.txt.lzw) is 1,976 bytes


## See also

- [LZ77 implementation](https://github.com/KareemMAX/lz77)
- [LZ78 implementation](https://github.com/KareemMAX/lz78)
- [Huffman coding implementation](https://github.com/mAshrafDawood/Huffman)