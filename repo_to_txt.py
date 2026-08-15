import os
import sys

def repo_to_txt(repo_path, output_file="repository_contents.txt"):
    # Folders to completely ignore
    ignore_dirs = {'node_modules', '__pycache__', 'venv', 'env', 'dist', 'build', '.git'}
    # File extensions to skip (images, compiled files, docs, etc.)
    ignore_exts = ('.png', '.jpg', '.pyc', '.exe', '.pdf', '.zip', '.o', '.bin', '.out', '.so', '.dll')
    
    with open(output_file, 'w', encoding='utf-8') as outfile:
        for root, dirs, files in os.walk(repo_path):
            
            # Modify `dirs` in-place so os.walk ignores hidden folders and the ignore_dirs list
            dirs[:] = [d for d in dirs if not d.startswith('.') and d not in ignore_dirs]
            
            for file in files:
                # Skip hidden files and known binary extensions
                if file.startswith('.') or file.endswith(ignore_exts):
                    continue
                
                file_path = os.path.join(root, file)
                try:
                    with open(file_path, 'r', encoding='utf-8') as infile:
                        content = infile.read()
                        outfile.write(f"\n{'='*40}\n")
                        outfile.write(f"FILE: {file_path}\n")
                        outfile.write(f"{'='*40}\n")
                        outfile.write(content)
                        outfile.write("\n")
                except UnicodeDecodeError:
                    # Silently skip any other binary files that aren't text
                    pass
                except Exception as e:
                    print(f"Skipped {file_path}: {e}")

if __name__ == "__main__":
    if len(sys.argv) < 2:
        print("Usage: python repo_to_txt.py <path_to_repo>")
    else:
        repo_path = sys.argv[1]
        repo_to_txt(repo_path)
        print(f"Done! Check 'repository_contents.txt' in {os.getcwd()}")
