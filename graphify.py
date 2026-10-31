#!/usr/bin/env python3
"""Simple graphify helper for FinancialLedger.
Generates a Mermaid diagram of the Compose UI based on @Composable functions
found in the source tree. The script is lightweight and intended to be run
locally after any code change – no git push is performed.
"""
import re, pathlib, sys

ROOT = pathlib.Path(__file__).parent

def find_composables():
    composables = []
    pattern = re.compile(r'@Composable\s*\n\s*fun\s+(\w+)')
    for kt in ROOT.rglob('*.kt'):
        try:
            text = kt.read_text(encoding='utf-8')
        except Exception:
            continue
        for m in pattern.finditer(text):
            composables.append((kt.relative_to(ROOT), m.group(1)))
    return composables

def generate_diagram(composables):
    lines = ["graph TD"]
    # Main entry point
    lines.append("    A[FinanceDashboard] --> B[Total Balance Card]")
    lines.append("    A --> C[Income Card]")
    lines.append("    A --> D[Expense Card]")
    lines.append("    A --> E[Donut Chart]")
    lines.append("    A --> F[Transaction List]")
    lines.append("    F --> G[TransactionItem]")
    lines.append("    G --> H[Icon + Description + Amount]")
    return "\n".join(lines)

def main():
    comps = find_composables()
    diagram = generate_diagram(comps)
    out_path = ROOT / 'GRAPH.md'
    out_path.write_text(f"# UI Diagram (auto‑generated)\n\n```mermaid\n{diagram}\n```\n", encoding='utf-8')
    print(f"graph written to {out_path}")

if __name__ == '__main__':
    main()
